# How to run the app

**Always run Maven from this folder** (the one that contains `pom.xml`).

In terminal:

```powershell
cd "Blood\Blood Maintanence And Donor Recruitment"
mvn spring-boot:run
```

Or from Desktop (adjust path to match your folder):

```powershell
cd "C:\Users\sange\OneDrive\Desktop\Blood Maintanence and Donor Recruitement\Blood\Blood Maintanence And Donor Recruitment"
mvn spring-boot:run
```

Then open: **http://localhost:8080** (or the port in `src/main/resources/application.properties`).

If you see "No plugin found for prefix 'spring-boot'", you are in the wrong directory — `cd` into the folder that contains `pom.xml`.
