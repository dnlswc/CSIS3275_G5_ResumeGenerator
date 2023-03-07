# CSIS3275_G5_ResumeGenerator

Tables:
1. users (id (PK), user_name, user_password)
2. resumes (id (PK), user_id (FK))
3. personal_info (id (PK), first_name, last_name, phone, email, profession, summary, resume_id (FK))
4. jobs (id (PK), title, employer, job_location, job_start_date, job_end_date, description, resume_id (FK))
5. education (id (PK), qualification, institution, edu_location, edu_start_date, edu_end_date, description, resume_id (FK))
6. misc_info (id (PK), skill, resume_id (FK))

Relationships:
1. users – resumes: one to many
1. resumes - personal_info: one to one
2. resumes – jobs: one to many
3. resumes – education: one to many
4. resumes – misc_info: one to many