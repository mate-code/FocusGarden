# Project Definition Document

---

## 1. General Information

**Project Name:**  FocusGarden

**Document Version:**  v0.0.1

**Date:**  10.03.2026

**Prepared by:**  Mateusz Pruss

**Client:**   Anyone who want to focus on anything.

Example 1: Student who wants to learn math.

Example 2: Teenager who wants to read more books.

**Stakeholders:**  None

**Project Purpose:**  
Project was inspared by popular focus technique called "Pomodoro", it avails people to set focus intervals by setting focus time and breaks time in funny way.

---

## 2. Project Overview

TBD

---

## 3. Business Objectives

-  TBD
-  
-  

**Success Metrics (KPIs):**

-  TBD
-  
-  

---

## 4. Project Scope

### 4.1 In Scope

-  TBD
-  
-  

### 4.2 Out of Scope

-  TBD
-  
-  

---

## 5. Assumptions & Constraints

### Assumptions

-  TBD
-  

### Constraints

- Budget  
- Timeline  
- Technology stack  
- Legal / compliance  

---

## 6. Functional Requirements (FR)

---

### Functional Requirements List

**FR-01 – Focus Timer**  

Description:  Availability to set to focus time

Priority:  Very High

Acceptance Criteria: User is able to set up his declared focus time and when time ends user will choose to take a break or start another focus session.

**FR-02 – Break Timer**  

Description:  Availability to set up break time

Priority:  Very High

Acceptance Criteria:  TBD

---

## 7. Non-Functional Requirements (NFR)

### 7.1 Performance

**NFR-01 – Response Time**  
System must respond within **< 300 ms for 95% of requests**.

---

### 7.2 Security

**NFR-02 – Authentication & Authorization**  
System must support OAuth2 / JWT-based authentication.

---

### 7.3 Availability & Reliability

**NFR-03 – Uptime**  
System uptime must be **≥ 99.9% monthly**.

---

### 7.4 Scalability

**NFR-04 – Horizontal Scalability**  
System must scale horizontally with no downtime.

---

### 7.5 Usability & UX

**NFR-05 – User Experience**  
A new user should complete the main flow within **< 2 minutes**.

---

### 7.6 Maintainability

**NFR-06 – Code Quality**  

- Code coverage ≥ 70%  
- Static analysis with no critical issues  

---

### 7.7 Compliance & Legal

**NFR-07 – Compliance**  
System must comply with **GDPR / ISO 27001 / SOC2 (if applicable)**.

---

## 8. Technical Requirements

- Frontend:  
- Backend:  
- Database:  
- Infrastructure:  
- CI/CD:  
- Monitoring & Logging:  

---

## 9. System Architecture

### 9.1 High-Level Architecture

Component description:

- Web / Mobile Client  
- API Gateway  
- Backend Services  
- Database  
- External Integrations  

---

### 9.2 Deployment Architecture

- Cloud provider  
- Regions  
- Environments (DEV / QA / STAGE / PROD)  

---

## 10. Data Model (High-Level)

Main entities:

### User

- id  
- email  
- role  
- status  

---

## 11. API Specification (Optional)

Reference to:

- OpenAPI / Swagger  
- Postman collection  

---

## 12. Use Cases

### UC-01 – User Login

**Actor:** User  

**Main Flow:**

1. User enters credentials  
2. System validates input  
3. System grants access  

---

## 13. Risks & Mitigation

| ID | Risk | Probability | Impact | Mitigation |
|------|-------|--------------|----------|--------------|
| R1 | Schedule delay | Medium | High | MVP first |

---

## 14. Milestones & Timeline

| Phase | Deliverables | Deadline |
|----------|----------------|-------------|
| Discovery | Requirements | |
| Design | UX + Architecture | |
| Development | Implementation | |
| Testing | QA + UAT | |
| Go-Live | Production | |

---

## 15. Acceptance Criteria

- All **High priority FRs implemented**  
- All **critical bugs fixed**  
- Performance & security requirements met  
- UAT signed-off  

---

## 16. Change Management

Any changes must be:

- documented  
- estimated  
- approved by stakeholders  

---

## 17. Approval

| Name | Role | Signature | Date |
|--------|--------|------------|--------|
|        |        |            |        |