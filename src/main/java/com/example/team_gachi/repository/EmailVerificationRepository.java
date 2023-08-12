package com.example.team_gachi.repository;

import org.springframework.data.repository.CrudRepository;

public interface EmailVerificationRepository<EmailVerification> extends CrudRepository<EmailVerification, String> {

}
