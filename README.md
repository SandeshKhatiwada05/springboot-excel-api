# Excel File Upload & Download - Spring Boot

A Spring Boot project demonstrating **Excel upload and downloadable Excel template generation** using **Apache POI**.

This project allows users to:

* Upload `.xlsx` Excel files
* Parse Excel rows into entities
* Store records in a database
* Download a predefined Excel template for consistent uploads

## Tech Stack

* Java
* Spring Boot
* Spring Data JPA
* Apache POI
* Lombok
* Maven

## Features

### Upload Excel

Upload structured Excel files and automatically save records into the database.

Expected format:

| Name      | Finisher            |
| --------- | ------------------- |
| John Cena | Attitude Adjustment |

### Download Template

Download a predefined Excel template to maintain consistent column structure before uploading.

## API Endpoints

```http
POST /upload
```

Upload Excel file.

```http
GET /template
```

Download Excel template.

## Supported Format

* `.xlsx`

## Learning Goals

This project demonstrates:

* Excel parsing with Apache POI
* File upload handling in Spring Boot
* Dynamic Excel generation
* Database persistence using JPA
* Clean service/helper layer separation

## Resource
- [Buidling Excel Upload and Download via spring boot](https://medium.com/@khatiwadasandesh501/building-excel-upload-download-functionality-in-spring-boot-950f9178bb41)

