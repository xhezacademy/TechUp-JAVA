package org.auk.todo.repository;

import org.auk.todo.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LabelRepository extends JpaRepository<Label,Long> {

    Optional<Label> findBySlug(String slug);
}
