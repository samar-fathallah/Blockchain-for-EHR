# Blockchain-for-EHR
blockchain application for Electronic Healthcare Records (EHR)

EHRs contain all information pertaining to a patient in the healthcare system. This includes general
information about the patient (name, age, weight, height, sex, initial medical measurements such
as blood pressure, pulse, oxygen, glucose, etc.), but also includes information about every visit to
a healthcare professional. The information about every visit includes any readings that the patient
takes in the clinic/hospital (blood pressure, glucose, temperature, pulse, etc.), the reason for the
visit (periodic checkup, management of a case such as hypertension or diabetes, or patient is
complaining about something), the doctor’s diagnosis, and the prescription. The prescription may
include medications (with doses and intake periods), referrals to other doctors/specialists, followup
appointments, and lab tests. Note that a lab test would be considered a separate visit to the
healthcare system.
In this project we created a blockchain application for the EHR operations mentioned
above. For each patient, an initial transaction is to be added to a block in the blockchain that
contains the general patient information. Then, for each visit a patient makes in the system, another
transaction will be added to the most recent block with the information of that visit. The transaction
for each patient will be chained in a similar way to the transactions of a user on the Bitcoin
blockchain. The following figure illustrates how the blockchain would look like after adding the
EHR of one patient.
![alt text](https://github.com/samar-fathallah/Blockchain-for-EHR/blob/main/image.JPG?raw=true)
