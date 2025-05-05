# Pokemon API

This is a simple REST API that provides information about the original 151 Pokémon. The data is stored in a JSON file.

## Endpoints

1. **GET api/pokemons** - List all available Pokémon.
2. **GET api/pokemons/{id}** - Retrieve information about a specific Pokémon by its ID.

## Running the Application

### Prerequisites

- Java 17 or newer
- Gradle 7 or newer

### Steps to run

1. Clone the repository:
    ```bash
    git clone https://github.com/GaashShmilovich/pokemon-api-task.git
    cd pokemon-api
    ```

2. Build the project:
    ```bash
    ./gradlew build
    ```

3. Run the Spring Boot application:
    ```bash
    ./gradlew bootRun
    ```

4. Access the API at `http://localhost:8080`.

   Example API calls:
   - `GET http://localhost:8080/pokemons`
   - `GET http://localhost:8080/pokemons/1`

## Notes

- This API currently only supports the original 151 Pokémon, and the data is read from the `src/main/resources/static/pokemons.json` file.

## Testing

### Running Tests

Run all tests using:
```bash
./gradlew test