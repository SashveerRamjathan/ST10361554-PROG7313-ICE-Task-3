import com.google.firebase.Timestamp

data class UserScore(
    val userId: String,
    val categoryName: String,
    val score: Int,
    val timestamp: Timestamp
)
