-- Updated for Microsoft SQL Server
IF EXISTS (SELECT * FROM sys.databases WHERE name = 'EPHARMACY_MEDICINEMS_DB')
BEGIN
    DROP DATABASE EPHARMACY_MEDICINEMS_DB;
END
GO

CREATE DATABASE EPHARMACY_MEDICINEMS_DB;
GO

USE EPHARMACY_MEDICINEMS_DB;
GO

CREATE TABLE MEDICINE (
    MEDICINE_ID INT IDENTITY(1,1) PRIMARY KEY,
    MEDICINE_NAME NVARCHAR(100) NOT NULL,
    MANUFACTURER NVARCHAR(50) NOT NULL,
    PRICE INT NOT NULL,
    DISCOUNT_PERCENT INT NOT NULL,
    MANUFACTURING_DATE DATE NOT NULL,
    EXPIRY_DATE DATE NOT NULL,
    CATEGORY NVARCHAR(50) NOT NULL,
    QUANTITY INT NOT NULL
);
GO

INSERT INTO MEDICINE (MEDICINE_NAME, MANUFACTURER, PRICE, DISCOUNT_PERCENT, MANUFACTURING_DATE, EXPIRY_DATE, CATEGORY, QUANTITY) VALUES
('Augmentin 625 Duo Tablet', 'Glaxo SmithKline Pharmaceuticals Ltd', 240, 15, '2022-02-20', '2022-02-25', 'Allopathy', 20),
('Allegra-M Tablet', 'Sanofi India Ltd', 340, 15, '2021-08-22', '2026-08-24', 'Allopathy', 15),
('Azee 500 Tablet', 'Cipla Ltd', 200, 20, '2022-08-22', '2024-11-08', 'Allopathy', 10),
('Ketosteril Tablet', 'Fresenius Kabi India Pvt Ltd', 324, 25, '2020-08-22', '2023-02-02', 'Allopathy', 20),
('Seroflo 250 Inhaler', 'Cipla Ltd', 450, 16, '2020-08-20', '2024-06-28', 'Allopathy', 4),
('Heptral 400mg Tablet', 'Abbott', 450, 20, '2022-08-22', '2025-12-12', 'Allopathy', 19),
('Foracort Inhaler 200', 'Cipla Ltd', 450, 30, '2021-06-22', '2025-09-01', 'Allopathy', 23),
('Azithral 500 Tablet', 'Alembic Pharmaceuticals Ltd', 150, 21, '2021-08-22', '2025-11-21', 'Allopathy', 11),
('Lipaglyn Tablet', 'Zydus Cadila', 250, 21, '2020-08-22', '2024-01-11', 'Allopathy', 10),
('Letroz Tablet', 'Sun Pharmaceutical Industries Ltd', 150, 20, '2020-08-22', '2023-11-21', 'Allopathy', 20),
('LizolID 600 Tablet', 'Integrace Pvt Ltd', 180, 14, '2020-08-22', '2025-09-11', 'Allopathy', 3),
('Lyrica 75mg Capsule', 'Pfizer Ltd', 550, 20, '2022-08-22', '2023-04-01', 'Allopathy', 5),
('L-Hist Mont Tablet', 'Alkem Laboratories Ltd', 120, 20, '2020-08-22', '2026-12-07', 'Allopathy', 10),
('PAN 40 Tablet', 'Alkem Laboratories Ltd', 150, 24, '2021-08-22', '2024-07-24', 'Allopathy', 11),
('Primolut-N Tablet', 'Zydus Cadila', 100, 25, '2023-01-01', '2024-08-01', 'Allopathy', 20),
('PlacIDa Tablet', 'Mankind Pharma Ltd', 150, 24, '2021-08-22', '2022-07-01', 'Allopathy', 11),
('Pantop-D SR Capsule', 'Aristo Pharmaceuticals Pvt Ltd', 150, 20, '2023-01-01', '2026-08-01', 'Allopathy', 1),
('PregabID NT Tablet', 'Intas Pharmaceuticals Ltd', 200, 20, '2023-01-01', '2025-09-11', 'Allopathy', 21),
('Progynova 2mg Tablet', 'Bayer Zydus Pharma Pvt Ltd', 110, 22, '2023-01-01', '2025-07-21', 'Allopathy', 4),
('Pregaba-M 75 Capsule', 'Torrent Pharmaceuticals Ltd', 150, 30, '2023-01-01', '2025-01-31', 'Allopathy', 3),
('Orthoron Paste 60 ml', 'Natural Remedies Pvt. Ltd.', 450, 30, '2022-08-12', '2025-09-11', 'Veterinary', 234),
('Bites Milk Flavour 75 gm', 'Natural Remedies Pvt. Ltd.', 350, 24, '2023-01-03', '2025-09-11', 'Veterinary', 88),
('Himalaya Erina EP Powder', 'Himalaya Wellness Company', 190, 24, '2023-01-03', '2025-09-11', 'Veterinary', 78),
('Himalaya Himcal Pet', 'Himalaya Wellness Company', 750, 26, '2022-01-04', '2024-09-10', 'Veterinary', 67),
('Mobility Bites', 'Natural Remedies Pvt. Ltd.', 180, 12, '2022-08-07', '2024-06-09', 'Veterinary', 88),
('Nefrotec DS Vet Tablets', 'Himalaya Wellness Company', 340, 15, '2022-06-09', '2025-07-12', 'Veterinary', 67),
('Whiskas Adult', 'Mars International India Pvt Ltd', 150, 20, '2023-01-03', '2026-09-08', 'Veterinary', 56),
('Wiggles Barkstix Dogs', 'Sixth Sense Retail Private Limited', 450, 40, '2023-01-03', '2024-09-10', 'Veterinary', 89),
('Wiggles Puppy Food', 'Sixth Sense Retail Private Limited', 1750, 50, '2023-01-03', '2025-06-11', 'Veterinary', 98),
('Anti Hairfall Shampoo', 'Natural Remedies Pvt. Ltd.', 155, 17, '2022-08-04', '2024-08-12', 'Veterinary', 56),
('Sugar Free Gold Powder', 'Mankind Pharma Ltd', 350, 40, '2023-07-01', '2025-05-11', 'Diabetes', 67),
('Pro360 Diabetic Powder', 'Sixth Sense Retail Private Limited', 280, 30, '2022-05-06', '2025-10-21', 'Diabetes', 68),
('Dr Morepen Test Strips', 'Himalaya Wellness Company', 150, 40, '2022-05-01', '2025-01-13', 'Diabetes', 87),
('Accu Instant Glucometer', 'Mankind Pharma Ltd', 200, 50, '2023-11-01', '2025-12-01', 'Diabetes', 89),
('Accu Active Test Strip', 'Himalaya Wellness Company', 260, 30, '2023-07-11', '2025-07-08', 'Diabetes', 90),
('Garlic Oil 500mg capsules', 'Natural Remedies Pvt. Ltd.', 210, 15, '2022-07-04', '2024-08-01', 'Diabetes', 98),
('Kapiva Karela Jamun Juice', 'Mankind Pharma Ltd', 240, 12, '2023-07-18', '2025-02-26', 'Diabetes', 75),
('Dprotin Chocolate Powder', 'Natural Remedies Pvt. Ltd.', 310, 25, '2022-08-01', '2024-07-09', 'Diabetes', 83),
('Nestle Opti Powder', 'Sixth Sense Retail Private Limited', 260, 30, '2023-05-15', '2025-01-27', 'Diabetes', 72),
('Onetouch Select Test Strip', 'Mankind Pharma Ltd', 150, 20, '2022-12-15', '2025-11-22', 'Diabetes', 86),
('Dettol Antiseptic Liquid', 'Alkem Laboratories Ltd', 250, 14, '2022-08-24', '2024-07-24', 'Covid Essentials', 87),
('Fastup Effervesent Tablet', 'Mankind Pharma Ltd', 170, 25, '2022-07-22', '2022-07-14', 'Covid Essentials', 88),
('Healthvit Omega3 Fish Oil', 'Himalaya Wellness Company', 180, 13, '2022-02-13', '2024-02-14', 'Covid Essentials', 78),
('Newnikpulse Oximeter', 'Alkem Laboratories Ltd', 210, 14, '2022-04-18', '2024-07-14', 'Covid Essentials', 79),
('Omron Compressor Nebulizer', 'Alkem Laboratories Ltd', 230, 23, '2022-09-19', '2024-03-24', 'Covid Essentials', 80),
('Piramal Tri Activ N95', 'Natural Remedies Pvt. Ltd.', 150, 14, '2022-09-09', '2025-06-24', 'Covid Essentials', 90),
('Surgical Hand Disinfectant', 'Alkem Laboratories Ltd', 150, 15, '2022-07-21', '2024-07-27', 'Covid Essentials', 92),
('Univen Powder', 'Mankind Pharma Ltd', 200, 13, '2022-05-22', '2024-07-24', 'Covid Essentials', 97),
('Vaporizer Steam Inhaler', 'Natural Remedies Pvt. Ltd.', 250, 14, '2022-01-21', '2024-06-24', 'Covid Essentials', 94),
('Stevia Dry Leaves', 'Himalaya Wellness Company', 190, 15, '2023-07-22', '2025-07-24', 'Covid Essentials', 95),
('Dabur Pudin Capsule', 'Himalaya Wellness Company', 170, 14, '2022-07-04', '2024-08-24', 'Ayush', 85),
('Liv52 Tablet', 'Natural Remedies Pvt. Ltd.', 190, 13, '2023-01-02', '2024-02-14', 'Ayush', 87),
('Dabur Chyawanprash', 'Alkem Laboratories Ltd', 210, 15, '2022-02-05', '2025-07-24', 'Ayush', 86),
('Dabur Gold Capsule', 'Mankind Pharma Ltd', 150, 20, '2022-08-09', '2025-08-24', 'Ayush', 88),
('Dabur Pudinhara Liquid', 'Natural Remedies Pvt. Ltd.', 240, 16, '2022-09-17', '2024-05-12', 'Ayush', 89),
('Zandubalm', 'Alkem Laboratories Ltd', 140, 15, '2022-02-24', '2025-03-24', 'Ayush', 97),
('Dabur Hajmola Tablets', 'Mankind Pharma Ltd', 250, 13, '2023-07-14', '2025-07-17', 'Ayush', 95),
('Orthoherb Tablet', 'Mars International India Pvt Ltd', 260, 15, '2022-06-13', '2024-06-04', 'Ayush', 96),
('Prosteez Tablet', 'Alkem Laboratories Ltd', 160, 15, '2023-11-24', '2025-10-24', 'Ayush', 92),
('Dabur Stresscom Capsules', 'Himalaya Wellness Company', 150, 14, '2023-07-19', '2025-07-19', 'Ayush', 94),
('Fortivirone Drops', 'Alkem Laboratories Ltd', 330, 40, '2022-05-24', '2024-07-24', 'Homeopathy', 97),
('Allen A84 Lipoma Drops', 'Mars International India Pvt Ltd', 450, 50, '2022-06-24', '2024-07-23', 'Homeopathy', 89),
('Sbl Scalptone Tablet', 'Natural Remedies Pvt. Ltd.', 430, 14, '2022-05-20', '2024-06-22', 'Homeopathy', 88),
('Sbl Vertefine Drops', 'Alkem Laboratories Ltd', 210, 14, '2022-05-12', '2024-07-24', 'Homeopathy', 87),
('Inhallergol Drops', 'Natural Remedies Pvt. Ltd.', 250, 14, '2022-05-24', '2024-12-24', 'Homeopathy', 78),
('Aloevera Bathing Soap', 'Alkem Laboratories Ltd', 150, 13, '2022-05-27', '2024-08-24', 'Homeopathy', 78),
('Allen Derma Plus Cream', 'Mars International India Pvt Ltd', 180, 14, '2022-12-12', '2024-07-24', 'Homeopathy', 96),
('Blood Urea Creatinin Drops', 'Himalaya Wellness Company', 410, 14, '2022-05-24', '2024-07-24', 'Homeopathy', 79),
('Wheezal Anti Snoring Drops', 'Alkem Laboratories Ltd', 400, 25, '2022-05-24', '2025-06-24', 'Homeopathy', 80),
('SBL Forenza', 'Himalaya Wellness Company', 380, 24, '2022-12-04', '2024-12-24', 'Homeopathy', 88),
('Revital Woman Tablet', 'Natural Remedies Pvt. Ltd.', 210, 15, '2022-06-21', '2024-05-24', 'Fitness', 81),
('Baconil Tts20 Patch', 'Himalaya Wellness Company', 240, 15, '2022-05-24', '2024-07-22', 'Fitness', 83),
('Dprotin Chocolate Powder', 'Alkem Laboratories Ltd', 250, 15, '2022-07-14', '2024-12-24', 'Fitness', 85),
('Nicotex Chew Gum', 'Natural Remedies Pvt. Ltd.', 220, 15, '2022-07-12', '2024-07-11', 'Fitness', 89),
('Nestle Vanilla Flavour', 'Natural Remedies Pvt. Ltd.', 170, 15, '2022-11-11', '2024-11-22', 'Fitness', 87),
('Horlicks Powder Malt', 'Mars International India Pvt Ltd', 190, 15, '2022-07-09', '2024-07-17', 'Fitness', 88),
('Garlic Pearls Capsule', 'Alkem Laboratories Ltd', 160, 30, '2022-05-24', '2025-07-24', 'Fitness', 80),
('Protinex Powder', 'Mars International India Pvt Ltd', 350, 20, '2023-07-24', '2025-07-24', 'Fitness', 86),
('Cadbury Bournvita', 'Himalaya Wellness Company', 450, 25, '2022-07-21', '2024-12-22', 'Fitness', 82),
('Hepapro Mixed Fruit Powder', 'Himalaya Wellness Company', 440, 40, '2022-06-24', '2024-07-14', 'Fitness', 84),
('Nestle Cerelac Powder', 'Himalaya Wellness Company', 156, 14, '2022-07-24', '2024-07-24', 'Mom & Baby', 97),
('Whisper Sanitary Pads', 'Alkem Laboratories Ltd', 180, 14, '2022-08-25', '2025-08-25', 'Mom & Baby', 98),
('Woodwards Gripe Water', 'Alkem Laboratories Ltd', 210, 15, '2022-09-25', '2024-05-24', 'Mom & Baby', 90),
('Cetaphil Baby Daily Lotion', 'Himalaya Wellness Company', 210, 14, '2022-05-22', '2024-07-24', 'Mom & Baby', 92),
('Johnsons Buds', 'Alkem Laboratories Ltd', 170, 13, '2022-11-11', '2025-07-27', 'Mom & Baby', 91),
('Pampers Baby Dry Pants', 'Natural Remedies Pvt. Ltd.', 155, 13, '2022-04-21', '2024-12-24', 'Mom & Baby', 88),
('Himalaya Baby Shampoo', 'Alkem Laboratories Ltd', 150, 14, '2022-03-22', '2024-02-24', 'Mom & Baby', 76),
('Himalaya Baby Powder', 'Alkem Laboratories Ltd', 180, 14, '2022-05-21', '2024-07-29', 'Mom & Baby', 89),
('Aptamil Infant Powder', 'Natural Remedies Pvt. Ltd.', 150, 13, '2022-11-22', '2024-07-01', 'Mom & Baby', 81),
('Himalaya Baby Cream', 'Alkem Laboratories Ltd', 170, 14, '2022-07-12', '2024-09-23', 'Mom & Baby', 72);
GO

SELECT * FROM MEDICINE;
GO

COMMIT;
