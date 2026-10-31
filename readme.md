# SBI Assignment – Nasabah REST API

REST API for managing nasabah (customer) data, developed using Spring Boot.

## Live Deployment

**Base URL:** https://sbi-assignment.onrender.com

## Tech Stack

- Java 25
- Spring Boot 4.1.2
- Spring Data JPA
- MySQL / TiDB Cloud
- Maven
- Flyway Database Migration
- Jakarta Bean Validation
- Docker
- Render

## Project Structure

```text
com.edwinkesuma.sbi_assignment
├── controller
│   └── NasabahController
├── dto
│   ├── CreateNasabahRequest
│   ├── UpdateNasabahRequest
│   └── NasabahResponse
├── entity
│   └── Nasabah
├── exception
│   ├── DuplicateResourceException
│   ├── ErrorResponse
│   ├── GlobalExceptionHandler
│   └── ResourceNotFoundException
├── mapper
│   └── NasabahMapper
├── repository
│   └── NasabahRepository
├── service
│   ├── NasabahService
│   └── impl
│       └── NasabahServiceImpl
└── utils
    ├── PhoneNumberNormalizer
    └── TextSanitizer
```

## API Endpoints

All endpoints use the `/api/nasabah` prefix.

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/nasabah` | Create a new nasabah |
| GET | `/api/nasabah` | Retrieve all active nasabah |
| GET | `/api/nasabah/{identityNumber}` | Retrieve nasabah by identity number |
| PUT | `/api/nasabah/{id}` | Update nasabah by ID |
| DELETE | `/api/nasabah/{id}` | Soft delete nasabah by ID |

## Request and Response Examples

All examples below use JSON.

### 1. Create Nasabah

**POST** `/api/nasabah`

Request body:

```json
{
  "fullName": "Budi Santoso",
  "address": "Jl. Merdeka No. 10, Jakarta",
  "birthPlace": "Jakarta",
  "birthDate": "1995-05-15",
  "identityNumber": "3174011505950001",
  "phone": "081234567890"
}
```

Success response: `201 Created`

```json
{
  "id": 1,
  "fullName": "Budi Santoso",
  "address": "Jl. Merdeka No. 10, Jakarta",
  "birthPlace": "Jakarta",
  "birthDate": "1995-05-15",
  "identityNumber": "3174011505950001",
  "phone": "6281234567890"
}
```

### 2. Get All Nasabah

**GET** `/api/nasabah`

Success response: `200 OK`

```json
[
  {
    "id": 1,
    "fullName": "Budi Santoso",
    "address": "Jl. Merdeka No. 10, Jakarta",
    "birthPlace": "Jakarta",
    "birthDate": "1995-05-15",
    "identityNumber": "3174011505950001",
    "phone": "6281234567890"
  }
]
```

### 3. Get Nasabah by Identity Number

**GET** `/api/nasabah/{identityNumber}`

Example:

```http
GET /api/nasabah/3174011505950001
```

Success response: `200 OK`

```json
{
  "id": 1,
  "fullName": "Budi Santoso",
  "address": "Jl. Merdeka No. 10, Jakarta",
  "birthPlace": "Jakarta",
  "birthDate": "1995-05-15",
  "identityNumber": "3174011505950001",
  "phone": "6281234567890"
}
```

### 4. Update Nasabah

**PUT** `/api/nasabah/{id}`

Request body:

```json
{
  "fullName": "Budi Santoso Updated",
  "address": "Jl. Sudirman No. 20, Jakarta",
  "birthPlace": "Jakarta",
  "birthDate": "1995-05-15",
  "phone": "6281234567890"
}
```

Success response: `200 OK`

```json
{
  "id": 1,
  "fullName": "Budi Santoso Updated",
  "address": "Jl. Sudirman No. 20, Jakarta",
  "birthPlace": "Jakarta",
  "birthDate": "1995-05-15",
  "identityNumber": "3174011505950001",
  "phone": "6281234567890"
}
```

Note: The identity number cannot be modified through this endpoint.

### 5. Delete Nasabah

**DELETE** `/api/nasabah/{id}`

Example:

```http
DELETE /api/nasabah/1
```

Success response: `200 OK`

```text
Nasabah berhasil dihapus
```

This endpoint uses soft delete. The record is marked as deleted instead of being physically removed from the database.

## Validation Rules

| Field | Validation |
|---|---|
| `fullName` | Required, maximum 100 characters |
| `address` | Required, maximum 255 characters |
| `birthPlace` | Required, maximum 100 characters |
| `birthDate` | Required, must not be in the future |
| `identityNumber` | Required, exactly 16 numeric digits |
| `phone` | Required, valid Indonesian mobile phone format |

### Phone Number Normalization

The API accepts Indonesian mobile phone formats such as:

- `081234567890`
- `6281234567890`
- `+6281234567890`

Phone numbers are normalized to the international Indonesian format starting with `62`.

## Error Handling

The API uses centralized exception handling through `GlobalExceptionHandler`.

All error responses follow this structure:

```json
{
  "status": 400,
  "message": "Nama lengkap wajib diisi",
  "error": "Bad Request",
  "timestamp": "2026-10-31T12:30:00"
}
```

### HTTP Error Responses

| HTTP Status | Error | Description |
|---|---|---|
| 400 | Bad Request | Request validation failed or invalid request format |
| 404 | Not Found | Requested nasabah record does not exist |
| 409 | Conflict | Identity number already exists |

### Example: 400 Bad Request

Occurs when request validation fails.

```json
{
  "status": 400,
  "message": "Nama lengkap wajib diisi",
  "error": "Bad Request",
  "timestamp": "2026-10-31T12:30:00"
}
```

Invalid date format example:

```json
{
  "status": 400,
  "message": "Format tanggal tidak valid. Gunakan format yyyy-MM-dd",
  "error": "Bad Request",
  "timestamp": "2026-10-31T12:30:00"
}
```

### Example: 404 Not Found

Occurs when the requested nasabah record cannot be found.

```json
{
  "status": 404,
  "message": "Nasabah dengan nomor ktp {identityNumber} tidak ditemukan",
  "error": "Not Found",
  "timestamp": "2026-10-31T12:30:00"
}
```

### Example: 409 Conflict

Occurs when attempting to create a nasabah with an identity number that already exists.

```json
{
  "status": 409,
  "message": "Nasabah dengan nomor ktp {identityNumber} sudah terdaftar",
  "error": "Conflict",
  "timestamp": "2026-10-31T12:30:00"
}
```

The `timestamp` field represents the time when the error occurred.

## Features

- CRUD operations for nasabah data.
- Input validation using Jakarta Bean Validation.
- Text sanitization.
- Indonesian phone number normalization.
- Soft delete implementation.
- Duplicate identity number handling.
- Centralized exception handling.
- Database schema migration using Flyway.
- JSON and XML support.

## Running Locally

### Prerequisites

- Java 25
- MySQL-compatible database

### Environment Variables

Configure the following environment variables:

| Variable | Description |
|---|---|
| `DB_URL` | JDBC database connection URL |
| `DB_USERNAME` | Database username |
| `DB_PASSWORD` | Database password |

### Run Application

```bash
./mvnw spring-boot:run
```

The application runs on port `8080` by default.

Flyway automatically applies database migrations during application startup.

## Docker

Build the Docker image:

```bash
docker build -t sbi-assignment .
```

Run the container:

```bash
docker run -d \
  -p 8080:8080 \
  -e DB_URL="your-jdbc-url" \
  -e DB_USERNAME="your-username" \
  -e DB_PASSWORD="your-password" \
  sbi-assignment
```

## Deployment

The application is deployed on Render using Docker.

The database is hosted on TiDB Cloud.

**Live API:** https://sbi-assignment.onrender.com

---

Developed as part of the SBI technical assignment.
