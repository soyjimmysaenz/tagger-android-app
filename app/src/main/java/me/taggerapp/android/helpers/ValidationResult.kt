package me.taggerapp.android.helpers

sealed class ValidationResult {
    object Success : ValidationResult()
    data class Error(val message: String, val tag: String) : ValidationResult()
}

data class FieldValidationException(
    override val message: String, val tag: String
) : Exception(message) {
    constructor(result: ValidationResult.Error) : this(result.message, result.tag)
}