# CSIS3275_G5_ResumeGenerator

Tables:
1. users (id (PK), email, user_password)
2. resumes (id (PK), user_id (FK))
3. personal_info (id (PK), full_name, resume_email, phone, resume_id (FK))
4. education (id (PK), degree, institution, grad_year, resume_id (FK))
5. jobs (id (PK), company, position, job_start_date, job_end_date, resume_id (FK))
6. misc_info (id (PK), skill, resume_id (FK))

Relationships:
1. users – resumes: one to many
1. resumes - personal_info: one to one
2. resumes – education: one to many
3. resumes – jobs: one to many
4. resumes – misc_info: one to many