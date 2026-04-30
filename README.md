# job-tracker-backend
JOB tracker api

job-tracker-backend/
├── src/main/java/com/jobtracker/
│   ├── JobTrackerApplication.java        ← main entry point
│   │
│   ├── config/
│   │   ├── SecurityConfig.java           ← Spring Security + CORS (Phase 3)
│   │   └── JwtConfig.java                ← JWT settings (Phase 3)
│   │
│   ├── controller/
│   │   ├── AuthController.java           ← /auth/register, /auth/login
│   │   └── JobApplicationController.java ← /api/jobs CRUD endpoints
│   │
│   ├── service/
│   │   ├── AuthService.java
│   │   └── JobApplicationService.java    ← business logic lives here
│   │
│   ├── repository/
│   │   ├── UserRepository.java
│   │   └── JobApplicationRepository.java ← extends JpaRepository
│   │
│   ├── entity/
│   │   ├── User.java                     ← JPA entity
│   │   └── JobApplication.java           ← JPA entity with Status enum
│   │
│   ├── dto/
│   │   ├── JobApplicationRequest.java    ← what client sends in
│   │   ├── JobApplicationResponse.java   ← what we send back
│   │   └── AuthRequest.java / AuthResponse.java
│   │
│   ├── exception/
│   │   ├── GlobalExceptionHandler.java   ← @ControllerAdvice (Phase 5)
│   │   └── ResourceNotFoundException.java
│   │
│   └── security/                         ← JWT filter, UserDetails (Phase 3)
│       ├── JwtUtil.java
│       └── JwtAuthFilter.java
│
├── src/main/resources/
│   └── application.yml                   ← DB config, JWT secret, CORS
│
└── pom.xml