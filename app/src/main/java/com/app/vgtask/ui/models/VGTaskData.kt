package com.app.vgtask.ui.models

data class VGTaskData<T>(
    val data: T?,
    val status: DataStatus
)

enum class DataStatus {
    DEFAULT,
    BUSY,
    PASSED,
    FAILED
}