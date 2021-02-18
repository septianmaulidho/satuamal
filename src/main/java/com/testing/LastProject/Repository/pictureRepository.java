package com.testing.LastProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testing.LastProject.Response.FileUploadResponse;

public interface pictureRepository extends JpaRepository<FileUploadResponse, String> {

}
