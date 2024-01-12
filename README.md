# Credit Card Validation

This project contains Java classes designed to validate credit card numbers and determine their associated payment system.

## Project Structure

The project comprises several classes:

### `CardNumber`

- Entry point of the application.
- Retrieves a card number from the user through the `ConsoleUtils` class.
- Initiates the validation process using the `CardNumberProcess` class.

### `CardNumberProcess`

- Validates the provided credit card number for length, digit-only content, and determines the associated payment system.
- Contains error messages for different validation scenarios.
- Displays the validation result along with any encountered errors.

### `ConsoleUtils`

- Obtains a card number from the user for validation.
- Displays a prompt to the user to enter a card number.

### `PaymentSystem` Enum

- Enumerates various payment systems along with their respective prefixes for detection.

### `PaymentSystem` Enum

- Lists the various errors with their corresponding descriptions.

## Important Logic

- The `CardNumberProcess` class performs validations for card number length and content.
- The `PaymentSystem` enum helps in detecting the payment system associated with the card number.

## Open Questions

- Does the project handle all possible payment systems?
- How does the project handle edge cases or exceptional scenarios?

## Usage

1. Run the `CardNumber` class.
2. Enter a card number for validation when prompted.
3. View the validation result.

## How to Contribute

Feel free to contribute by expanding the validation logic, adding support for additional payment systems, or improving error handling.
