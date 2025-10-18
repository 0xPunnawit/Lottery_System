# 🎟️ Lottery System - Java Spring Boot + PostgreSQL + Docker + AWS EC2

โปรเจกต์นี้เป็นระบบ **ซื้อ-ขายลอตเตอรี่ (Lottery Management System)** ที่พัฒนาโดยใช้ **Java Spring Boot**  
เชื่อมต่อฐานข้อมูล **PostgreSQL** และรองรับการทำงานผ่าน **Docker Compose**  
พร้อมสามารถนำไป Deploy บน **AWS EC2 (Ubuntu)**

---

## 📚 เนื้อหาในโปรเจกต์

🎬 [คลิปสาธิตการทำงาน (YouTube)](https://youtu.be/zhnP4oVKoNw)

- **Spring Boot Backend**
    - RESTful API สำหรับสมัครสมาชิก, เข้าสู่ระบบ, ซื้อ-คืนลอตเตอรี่, และดูประวัติการซื้อ
    - ใช้ **JWT (JSON Web Token)** เพื่อจัดการ Authentication & Authorization
    - ใช้ **Spring Security** ควบคุมสิทธิ์การเข้าถึง (USER / ADMIN)
    - ใช้ **Flyway** จัดการ Database Migration
- **PostgreSQL Database**
    - เก็บข้อมูลผู้ใช้ (Users), ลอตเตอรี่ (Lottery) และประวัติการซื้อ (UserTicket)
    - ใช้ Volume ของ Docker เก็บข้อมูลแบบถาวร (Persistent Data)
- **Docker Compose**
    - จัดการทั้ง App และ Database ให้อยู่ใน Network เดียวกัน
    - Build และ Deploy ระบบได้เพียงคำสั่งเดียว
- **Deploy บน AWS EC2**
    - ใช้เครื่อง Ubuntu ติดตั้ง Docker และ Docker Compose
    - สามารถรันระบบแบบ Production ได้ทันทีผ่าน IP ของ EC2 Instance

---

## ⚙️ เทคโนโลยีที่ใช้

| หมวดหมู่ | รายละเอียด |
|-----------|-------------|
| **Backend** | Java 17, Spring Boot, Spring Security, JPA (Hibernate) |
| **Database** | PostgreSQL 16 |
| **Migration Tool** | Flyway |
| **Containerization** | Docker, Docker Compose |
| **Cloud Platform** | AWS EC2 (Ubuntu) |
| **Tools** | Maven, Swagger UI, spring-dotenv |
| **Authentication** | JWT Token |

---

## 📁 โครงสร้างโปรเจกต์ (โดยสรุป)

src/
├── main/
│   ├── java/com/punnawit/Lottery_System
│   │   ├── business/          🧠 → ชั้นประมวลผลธุรกิจหลัก  
│   │   ├── config/            🔐 → ตั้งค่า Security, JWT และ Filter  
│   │   ├── controller/        📦 → API Controller (Auth, Lottery, User, Admin)  
│   │   ├── dto/               💬 → Request / Response DTO  
│   │   │    ├── request/      ✉️ → DTO สำหรับรับข้อมูลจาก client  
│   │   │    └── response/     📤 → DTO สำหรับส่งข้อมูลกลับไปยัง client  
│   │   ├── entity/            🧱 → Entity ของตารางใน Database (Users, Lottery, UserTicket)  
│   │   ├── exception/         🚨 → Custom Exception + Global Exception Handler  
│   │   ├── repository/        🗂️ → ติดต่อฐานข้อมูลผ่าน JPA Repository  
│   │   ├── service/           ⚙️ → ชั้นจัดการ logic ธุรกิจ (AuthService, LotteryService ฯลฯ)  
│   │   └── util/              🧩 → Utility เช่น TokenUtil, UserIdGenerator  
│   └── resources/
│       ├── db/migration/      🧾 → ไฟล์ SQL ของ Flyway (V1__...V6__)  
│       ├── application.yml    ⚙️ → ตั้งค่าระบบ (port, datasource, swagger)  
│       └── .env               🔑 → Environment Variables สำหรับ Docker  
├── Dockerfile                 🐳 → คำสั่ง build image ของ Spring Boot  
├── docker-compose.yml         🧩 → รวม service (App + PostgreSQL)  
└── README.md                  📝 → เอกสารอธิบายโปรเจกต์  

---

### เริ่มต้นระบบ (Build + Run)
```bash
docker compose up -d --build
```
---

เข้าไปดูฐานข้อมูล PostgreSQL
```bash
docker exec -it lottery_pg psql -U user_db -d lottery_db
```
`SELECT * FROM lottery_schema.users;`

`SELECT * FROM lottery_schema.lottery;`

`SELECT * FROM lottery_schema.user_ticket;`