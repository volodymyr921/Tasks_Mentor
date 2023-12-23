# Credit Card Validation

This project contains Java classes designed to validate credit card numbers and determine their associated payment system.

## Project Structure

The project comprises several classes:

### `CreditCardNumber`

- Entry point of the application.
- Retrieves a card number from the user through the `UserCardNumber` class.
- Initiates the validation process using the `ValidationCreditCardNumber` class.

### `ValidationCreditCardNumber`

- Validates the provided credit card number for length, digit-only content, and determines the associated payment system.
- Contains error messages for different validation scenarios.
- Displays the validation result along with any encountered errors.

### `UserCardNumber`

- Obtains a card number from the user for validation.
- Displays a prompt to the user to enter a card number.

### `PaymentSystem` Enum

- Enumerates various payment systems along with their respective prefixes for detection.

## Important Logic

- The `ValidationCreditCardNumber` class performs validations for card number length and content.
- The `PaymentSystem` enum helps in detecting the payment system associated with the card number.

## Open Questions

- Does the project handle all possible payment systems?
- How does the project handle edge cases or exceptional scenarios?

## Usage

1. Run the `CreditCardNumber` class.
2. Enter a card number for validation when prompted.
3. View the validation result.

## How to Contribute

Feel free to contribute by expanding the validation logic, adding support for additional payment systems, or improving error handling.
