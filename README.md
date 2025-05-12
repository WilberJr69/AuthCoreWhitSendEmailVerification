# AuthCoreWhitSendEmailVerification

Este proyecto es una **base sólida para futuros desarrollos full stack**, implementando autenticación y verificación de correo con **Spring Boot y JWT**.

## ✅ Funcionalidades principales

- Registro de usuario con envío de **link de verificación al correo**.
- Tokens con control de uso y expiración (`isUsed`, `isExpired`, `createdAt`, `expiresAt`).
- Validación automática de tokens expirados cada 5 minutos.
- Autenticación con JWT y control de acceso por **roles** (`ROLE_PRE_USER`, `ROLE_USER`).
- Arquitectura en capas (Controller, Service, Repository, DTO, Entity).
- CORS habilitado para expansión a frontend (FullStack).

## 🧩 Tecnologías

- Spring Boot (Web, Security, Mail, JPA, Validation)
- MySQL
- JWT (`jjwt`)
- Lombok

## 🚀 Futuro

Con esta base solo queda implementar los **CRUDs necesarios según roles** y conectar con un **frontend**. Esta estructura ya gestiona usuarios, roles, seguridad y verificación de identidad por correo.

---

