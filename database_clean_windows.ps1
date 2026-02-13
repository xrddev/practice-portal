Set-Location $PSScriptRoot

Write-Host "Cleaning database..."

mvn org.flywaydb:flyway-maven-plugin:9.22.0:clean `
  "-Dflyway.url=jdbc:mysql://localhost:3306/practice_portal_db" `
  "-Dflyway.user=uoi_db_admin" `
  "-Dflyway.password=Uoi2025!practiceportal" `
  "-Dflyway.cleanDisabled=false"

Write-Host "Clean complete. Now migrating..."

mvn org.flywaydb:flyway-maven-plugin:9.22.0:migrate `
  "-Dflyway.url=jdbc:mysql://localhost:3306/practice_portal_db" `
  "-Dflyway.user=uoi_db_admin" `
  "-Dflyway.password=Uoi2025!practiceportal"

Write-Host "Migration complete."
