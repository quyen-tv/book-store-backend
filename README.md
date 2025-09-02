# Book Store Backend

[![Java Version](https://img.shields.io/badge/Java-21-blue.svg)](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-4.0.0-red.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](https://opensource.org/licenses/MIT)

## Giới thiệu

**Book Store Backend** là một ứng dụng RESTful API được xây dựng bằng Spring Boot, cung cấp các dịch vụ backend cho một hệ thống quản lý cửa hàng sách trực tuyến. Dự án bao gồm các chức năng quản lý người dùng, phân quyền, sách, danh mục và xác thực bảo mật bằng JWT.

## Các tính năng chính

-   **Xác thực & Phân quyền:**
    -   Đăng nhập bằng tên người dùng và mật khẩu.
    -   Bảo mật API sử dụng JSON Web Tokens (JWT).
    -   Cơ chế làm mới token (Refresh Token).
    -   Phân quyền dựa trên vai trò (Role-Based Access Control).
-   **Quản lý Người dùng:**
    -   Tạo, đọc, cập nhật và xóa (CRUD) thông tin người dùng.
    -   Lấy thông tin người dùng đang đăng nhập.
    -   Tải lên và cập nhật ảnh đại diện (avatar).
-   **Quản lý Sách:**
    -   CRUD cho sách.
    -   Lọc và tìm kiếm sách theo tiêu đề, tác giả, danh mục, khoảng giá.
    -   Phân trang kết quả.
-   **Quản lý Danh mục:**
    -   CRUD cho các danh mục sách.
-   **Quản lý Vai trò & Quyền hạn:**
    -   CRUD cho các vai trò (ví dụ: ADMIN, USER).
    -   CRUD cho các quyền hạn chi tiết và gán chúng cho vai trò.
-   **Tài liệu API:**
    -   Tự động tạo tài liệu API với Swagger (OpenAPI).

## Công nghệ sử dụng

-   **Backend:**
    -   **Java 21**: Ngôn ngữ lập trình chính.
    -   **Spring Boot 3.x**: Framework chính để xây dựng ứng dụng.
    -   **Spring Security**: Xử lý xác thực và phân quyền với JWT.
    -   **Spring Data JPA / Hibernate**: Tương tác với cơ sở dữ liệu quan hệ.
    -   **MySQL**: Hệ quản trị cơ sở dữ liệu.
    -   **Spring Data Redis**: Sử dụng Redis để caching và quản lý token.
    -   **MapStruct**: Tự động chuyển đổi giữa các đối tượng DTO và Entity.
    -   **Lombok**: Giảm thiểu code boilerplate (getter, setter, constructor,...).
    -   **Cloudinary**: Dịch vụ lưu trữ và quản lý hình ảnh trên cloud.
-   **Build & Quản lý Dependencies:**
    -   **Maven**: Công cụ quản lý dự án và build.
-   **API Documentation:**
    -   **Springdoc OpenAPI**: Tích hợp Swagger UI để trực quan hóa và kiểm thử API.
-   **Containerization:**
    -   **Docker & Docker Compose**: Đóng gói và chạy ứng dụng cùng các dịch vụ phụ thuộc (MySQL, Redis) một cách dễ dàng.

## Yêu cầu cài đặt

Để chạy dự án này, bạn cần cài đặt các công cụ sau trên máy của mình:
-   JDK 21
-   Maven 3.8+
-   Docker và Docker Compose (khuyến khích)
-   Một instance MySQL đang chạy
-   Một instance Redis đang chạy

## Hướng dẫn cài đặt và Chạy ứng dụng

### 1. Clone repository

```bash
git clone https://github.com/quyen-tv/book-store-backend.git
cd book-store-backend
```

### 2. Cấu hình ứng dụng

Mở tệp `src/main/resources/application.yaml` và cập nhật các thông tin cấu hình cho phù hợp với môi trường của bạn:

-   **Cơ sở dữ liệu (MySQL):**
    ```yaml
    spring:
      datasource:
        url: jdbc:mysql://localhost:3306/your_database_name
        username: your_username
        password: your_password
    ```
-   **Redis:**
    ```yaml
    spring:
      data:
        redis:
          host: localhost
          port: 6379
    ```
-   **JWT Secret Key:**
    ```yaml
    jwt:
      signerKey: "your-very-strong-and-long-secret-key-that-is-at-least-256-bits"
    ```
-   **Cloudinary:**
    ```yaml
    cloudinary:
      cloud_name: "your_cloud_name"
      api_key: "your_api_key"
      api_secret: "your_api_secret"
    ```

### 3. Build dự án

Sử dụng Maven để build dự án:
```bash
./mvnw clean install
```

### 4. Chạy ứng dụng

Bạn có thể chạy ứng dụng bằng lệnh sau:
```bash
./mvnw spring-boot:run
```
Ứng dụng sẽ khởi động và chạy trên cổng `8080` (mặc định).

### 5. Chạy bằng Docker Compose (Khuyến khích)

Đây là cách đơn giản nhất để khởi chạy toàn bộ hệ thống, bao gồm ứng dụng, MySQL và Redis mà không cần cài đặt chúng trực tiếp trên máy.

1.  **Cấu hình Docker:**
    -   Đảm bảo các thông tin trong `application.yaml` trỏ đến tên service trong `docker-compose.yml` (ví dụ: `host.docker.internal` hoặc tên service `mysql`, `redis`).
2.  **Chạy Docker Compose:**
    Từ thư mục gốc của dự án, chạy lệnh:
    ```bash
    docker-compose up --build
    ```
    Lệnh này sẽ build image cho ứng dụng và khởi chạy tất cả các container cần thiết.

## Tài liệu API (Swagger)

Sau khi ứng dụng đã khởi động, bạn có thể truy cập vào giao diện Swagger để xem tài liệu chi tiết về các API và thực hiện gọi thử:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Để sử dụng các API yêu cầu xác thực, hãy làm theo các bước sau:
1.  Sử dụng API `/auth/token` để đăng nhập và nhận `accessToken`.
2.  Nhấn nút **"Authorize"** ở góc trên bên phải.
3.  Trong ô "Value", nhập `Bearer <your_access_token>` và nhấn **"Authorize"**.

## Cấu trúc dự án

```
.
├── src
│   ├── main
│   │   ├── java/com/quyentv/bookstorebackend
│   │   │   ├── configuration   # Cấu hình Spring Security, OpenAPI, Redis,...
│   │   │   ├── constant        # Các hằng số, enum (ví dụ: vai trò)
│   │   │   ├── controller      # Các REST API endpoints
│   │   │   ├── dto             # Data Transfer Objects (request/response)
│   │   │   ├── entity          # Các đối tượng JPA được map với bảng CSDL
│   │   │   ├── exception       # Xử lý exception toàn cục và các exception tùy chỉnh
│   │   │   ├── mapper          # MapStruct mappers
│   │   │   ├── repository      # Spring Data JPA repositories
│   │   │   ├── service         # Chứa business logic
│   │   │   └── ...
│   │   └── resources
│   │       └── application.yaml # Tệp cấu hình chính của ứng dụng
│   └── test                    # Chứa các unit/integration tests
├── pom.xml                     # Tệp cấu hình Maven
├── Dockerfile                  # Định nghĩa cách build Docker image cho ứng dụng
└── docker-compose.yml          # Định nghĩa các service để chạy ứng dụng với Docker
```

