package org.auk;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import org.auk.api.GithubUser;
import org.auk.api.GithubUserService;
import org.auk.api.Repo;
import org.auk.entity.ArticleDao;
import org.auk.entity.UserDao;
import org.auk.net.ServiceGenerator;
import org.auk.util.HashUtil;
import retrofit2.Response;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

/**
 * Spark Web Framework & REST API!
 *
 * @link http://www.jsonschema2pojo.org/
 */
public class App {
    public static void main(String[] args) {
        staticFiles.location("/public");
        port(8080);
        get("/", (req, res) -> {
            var model = Map.of("name", "Arian", "articles", new ArticleDao().getAll());
            return render(model, "index.hbs");
        });

        get("/articles/:slug", (req, res) -> {
            String slug = req.params(":slug");
            final var article = new ArticleDao().findBySlug(slug);

            if (article.isPresent()) {
                return render(Map.of("article", article.get()), "article.hbs");
            }

            return "Not found!";
        });

        get("/login", (req, res) -> render(new HashMap<>(), "login.hbs"));
        get("/register", (req, res) -> render(new HashMap<>(), "register.hbs"));

        post("/login", (req, res) -> {
            Map<String, String> errors = new HashMap<>();
            Map<String, String> fieldMap = new HashMap<>();

            String[] params = req.body().split("&");
            for (String value : params) {
                var field = value.split("=");
                fieldMap.put(field[0], field[1]);
            }

            var username = fieldMap.get("username");
            var password = fieldMap.get("password");

            if (username.isEmpty() || password.isEmpty()) {
                errors.put("error", "Empty username and/or password!");
                res.redirect("/login");
                return null;
            }

            var myUser = new UserDao().findByUsername(username);
            if (myUser.isEmpty()) {
                res.header("error", "User not found!");
                res.redirect("/login");
                return null;
            }

            // compare incoming password's hash to the existing user password
            if (HashUtil.passwordVerify(password, myUser.get().getPassword())) {
                res.header("error", "Passwords do not match!");
                res.redirect("/login");
                return null;
            }

            req.session(true).attribute("user", username);
            res.redirect("/articles");
            return null;
        });

        post("/register", (req, res) -> {
            return null;
        });

        get("/hello/:name", (req, res) -> {
            String name = req.params(":name");
            return "Hello " + name + "!";
        });

        path("/github", () -> {
            Gson gson = new Gson();

            get("/user/my_repos", (req, res) -> {
                GithubUserService service = ServiceGenerator.createAuthorizedService(GithubUserService.class);
                Response<JsonArray> myRepos = service.getMyRepos("private").execute();

                Type listType = new TypeToken<List<Repo>>() {}.getType();
                return new Gson().fromJson(myRepos.body(), listType);
            }, gson::toJson);

            get("/users/:username", (req, res) -> {
                String username = req.params(":username");
                GithubUserService service = ServiceGenerator.createService(GithubUserService.class);
                GithubUser user = null;

                try {
                    Response<GithubUser> response = service.getByUsername(username).execute();
                    user = response.body();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return user;
            }, gson::toJson);
            get("/users/:username/repos", (req, res) -> {
                String username = req.params(":username");
                GithubUserService service = ServiceGenerator.createService(GithubUserService.class);

                try {
                    Response<JsonArray> response = service.getUserRepos(username).execute();
                    Repo repo = gson.fromJson(response.body(), Repo.class);
                    System.out.println(repo.getFullName());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return "";
            });
        });

        enableDebugScreen();
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static void print(String... messages) {
        for (var val : messages) {
            System.out.println(val + System.lineSeparator());
        }
    }
}
