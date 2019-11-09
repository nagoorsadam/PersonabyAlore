package com.plash.configurator.repository;

import com.plash.configurator.model.EmailTemplates;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailTemplatesRepository extends JpaRepository<EmailTemplates,Long> {

    EmailTemplates findFirstByName(String templateName);
}
