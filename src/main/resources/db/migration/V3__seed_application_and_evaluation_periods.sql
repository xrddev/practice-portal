-- ApplicationPeriod seed
INSERT INTO application_period (start_time, end_time, status_mode)
SELECT '2025-09-01 00:00:00', '2025-12-31 23:59:59', 'AUTOMATIC'
WHERE NOT EXISTS (SELECT 1 FROM application_period);

-- EvaluationPeriod seed
INSERT INTO evaluation_period (start_time, end_time, status_mode)
SELECT '2026-01-01 00:00:00', '2026-03-31 23:59:59', 'AUTOMATIC'
WHERE NOT EXISTS (SELECT 1 FROM evaluation_period);