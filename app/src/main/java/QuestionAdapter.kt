import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.st10361554.quizapp.R

class QuestionAdapter(private val questions: List<Question>, private val onAnswerSelected: (Int) -> Unit)
    : RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>()
{
    class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionText: TextView = itemView.findViewById(R.id.questionText)
        val optionButtons: List<Button> = listOf(
            itemView.findViewById(R.id.option1),
            itemView.findViewById(R.id.option2),
            itemView.findViewById(R.id.option3),
            itemView.findViewById(R.id.option4)
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.question_item, parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val question = questions[position]
        holder.questionText.text = question.questionText
        for (i in 0..3) {
            holder.optionButtons[i].text = question.options[i]
            holder.optionButtons[i].setOnClickListener { onAnswerSelected(i) }
        }
    }

    override fun getItemCount() = questions.size
}