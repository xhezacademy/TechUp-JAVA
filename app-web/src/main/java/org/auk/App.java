package org.auk;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import org.auk.api.GithubUserService;
import org.auk.api.Repo;
import org.auk.api.User;
import org.auk.entity.ArticleDao;
import org.auk.net.ServiceGenerator;
import retrofit2.Response;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.io.IOException;
import java.lang.reflect.Type;
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

        get("/", (req, res) -> {
            var model = Map.of("name", "Arian", "articles", new ArticleDao().getAll());
            return render(model, "index.hbs");
        });

        get("/:slug", (req, res) -> {
            String slug = req.params(":slug");
            final var article = new ArticleDao().findBySlug(slug);

            if (article.isPresent()) {
                return render(Map.of("article", article.get()), "article.hbs");
            }

            return "Not found!";
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
                User user = null;

                try {
                    Response<User> response = service.getByUsername(username).execute();
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
}
