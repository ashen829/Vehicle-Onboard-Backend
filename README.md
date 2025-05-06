
# Vehicle Onboard System 🚗

## 📌 Project Description
This is a Spring Boot-based backend application designed for managing vehicle onboarding. It supports vehicle registration along with image uploads categorized by tags like `MAIN`, `FRONT`, `BACK`, `LEFT`, `RIGHT`, etc. The system is built to store vehicle data and associated images in a local directory while storing only file paths in the database.

---

## 🛠️ How to Set Up the Project

1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-username/vehicle-onboard.git
   cd vehicle-onboard
   ```

2. **Install Node Modules (if there's a frontend or for dev tooling)**
   If your project includes frontend components or uses Node.js scripts:
   ```bash
   npm install
   ```

3. **Set Up File Saving Directory**
    - Create a directory on your local system:
      ```
      D:\Ashen\Vehicle-Onboard\data\
      ```
    - This directory will be used to save uploaded vehicle and model images.

4. **Update `application.properties`**
   Make sure this line is present in your Spring Boot configuration:
   ```properties
   vehicle.image.upload-dir=D:\Ashen\Vehicle-Onboard
   ```

---

## 🚀 Implemented Endpoints

| Method | Endpoint                    | Description                       |
|--------|-----------------------------|-----------------------------------|
| POST   | `/vehicles/save`           | Save a new vehicle with images    |
| POST   | `/make/save`               | Save a new vehicle make (brand)   |
| POST   | `/model/save`              | Save a new model under a make     |

---

## 📫 Sending Requests Using Postman

### 1. **Save Vehicle with Images**

- **URL**: `POST /vehicles/save`
- **Content-Type**: `multipart/form-data`
- **Form Data:**

| Key            | Type     | Example Value         |
|----------------|----------|------------------------|
| regNo          | Text     | `ABC-1234`             |
| make.id        | Text     | `1`                    |
| model.id       | Text     | `5`                    |
| yearOfManu     | Text     | `2020`                 |
| fuelType       | Text     | `PETROL`               |
| vehicleType    | Text     | `SUV`                  |
| images         | File     | Choose image file      |
| tags           | Text     | `FRONT`                |
| images         | File     | Choose another image   |
| tags           | Text     | `BACK`                 |

> 🔁 You can add multiple `images` and `tags` pairs. They must match by index.

---

### 2. **Save Make**

- **URL**: `POST /make/save`
- **Content-Type**: `multipart/form-data`

| Key      | Type | Example Value    |
|----------|------|------------------|
| name     | Text | `Toyota`         |
| logo     | File | logo.png         |

---

### 3. **Save Model**

- **URL**: `POST /model/save`
- **Content-Type**: `application/json`

```json
{
  "name": "Corolla",
  "makeId": 1,
  "vehicleType": "CAR"
}
```

---

## 📂 Directory Structure Example

```
D:\Ashen\Vehicle-Onboard
│
└───data
    ├───model
    └───vehicle
```

---

## 🧾 Notes

- `fuelType`, `vehicleType`, and `tags` must match the enums in your backend.
- Use Postman or similar REST tools to test the APIs.
- Directory paths are OS-specific; update `upload-dir` accordingly.

---

## 📧 Contact

For any queries or contributions, please open an issue or contact the maintainer.
