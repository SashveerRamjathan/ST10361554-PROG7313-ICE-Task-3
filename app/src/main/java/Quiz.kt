class Quiz {

    val quizQuestions = listOf(
        Category(
            "General Knowledge", listOf(
                Question("What is the capital of France?", listOf("Berlin", "Madrid", "Paris", "Rome"), 2),
                Question("What planet is known as the Red Planet?", listOf("Venus", "Mars", "Jupiter", "Saturn"), 1),
                Question("Who wrote Romeo and Juliet?", listOf("Charles Dickens", "J.K. Rowling", "William Shakespeare", "Mark Twain"), 2),
                Question("What is the largest ocean on Earth?", listOf("Atlantic", "Indian", "Arctic", "Pacific"), 3),
                Question("How many continents are there?", listOf("5", "6", "7", "8"), 2),
                Question("What is the chemical symbol for gold?", listOf("Au", "Ag", "Pb", "Fe"), 0),
                Question("How many players are in a standard soccer team?", listOf("9", "10", "11", "12"), 2),
                Question("What is the square root of 64?", listOf("6", "7", "8", "9"), 2),
                Question("Who painted the Mona Lisa?", listOf("Michelangelo", "Vincent van Gogh", "Leonardo da Vinci", "Pablo Picasso"), 2),
                Question("What is the smallest country in the world?", listOf("Monaco", "Liechtenstein", "Vatican City", "Malta"), 2)
            )
        ),
        Category(
            "Computer Science", listOf(
                Question("What does CPU stand for?", listOf("Central Processing Unit", "Computer Power Unit", "Central Program Utility", "Core Processing User"), 0),
                Question("Which language is mainly used for Android development?", listOf("Swift", "Kotlin", "JavaScript", "C#"), 1),
                Question("What is the full form of HTML?", listOf("HyperText Markup Language", "HighTech Modern Language", "Hyperlinking Text Management Language", "HyperTransfer Markup Logic"), 0),
                Question("What is the default port for HTTP?", listOf("80", "22", "443", "8080"), 0),
                Question("Which data structure follows LIFO?", listOf("Queue", "Stack", "Array", "Linked List"), 1),
                Question("What does SQL stand for?", listOf("Sequential Query Language", "Structured Query Language", "Server Query Logic", "System Query Language"), 1),
                Question("What is the binary representation of 10?", listOf("1000", "1010", "1100", "1001"), 1),
                Question("Which of these is NOT an OOP principle?", listOf("Abstraction", "Encapsulation", "Compilation", "Polymorphism"), 2),
                Question("Which company developed Python?", listOf("Microsoft", "Google", "Guido van Rossum", "Apple"), 2),
                Question("What does RAM stand for?", listOf("Random Access Memory", "Readable Arithmetic Module", "Rapid Allocation Memory", "Run-time Application Model"), 0)
            )
        ),
        Category(
            "Science and Nature", listOf(
                Question("What gas do plants absorb for photosynthesis?", listOf("Oxygen", "Nitrogen", "Carbon Dioxide", "Hydrogen"), 2),
                Question("What is the powerhouse of the cell?", listOf("Nucleus", "Ribosome", "Mitochondria", "Cytoplasm"), 2),
                Question("What is the chemical formula for water?", listOf("H2O", "CO2", "O2", "CH4"), 0),
                Question("What type of animal is a Komodo dragon?", listOf("Mammal", "Bird", "Reptile", "Amphibian"), 2),
                Question("What is the hardest natural substance on Earth?", listOf("Gold", "Diamond", "Iron", "Quartz"), 1),
                Question("How many bones are in the human body?", listOf("200", "206", "210", "180"), 1),
                Question("What planet has the most moons?", listOf("Earth", "Jupiter", "Saturn", "Uranus"), 1),
                Question("What is the most abundant gas in Earth’s atmosphere?", listOf("Oxygen", "Nitrogen", "Carbon Dioxide", "Argon"), 1),
                Question("What part of the human body produces insulin?", listOf("Liver", "Kidney", "Pancreas", "Stomach"), 2),
                Question("What is the atomic number of Hydrogen?", listOf("0", "1", "2", "8"), 1)
            )
        ),
        Category(
            "Movies and Pop Culture", listOf(
                Question("Which movie features the song 'Let It Go'?", listOf("Moana", "Frozen", "Tangled", "The Lion King"), 1),
                Question("Who played Iron Man in the Marvel movies?", listOf("Chris Hemsworth", "Robert Downey Jr.", "Chris Evans", "Mark Ruffalo"), 1),
                Question("What is the highest-grossing movie of all time?", listOf("Avatar", "Titanic", "Avengers: Endgame", "Star Wars"), 0),
                Question("Who is the main character in 'Breaking Bad'?", listOf("Jesse Pinkman", "Hank Schrader", "Walter White", "Gus Fring"), 2),
                Question("What is the name of the school in 'Harry Potter'?", listOf("Durmstrang", "Hogwarts", "Beauxbatons", "Ilvermorny"), 1),
                Question("What year was the first 'Star Wars' movie released?", listOf("1975", "1977", "1980", "1983"), 1),
                Question("Who directed 'Titanic'?", listOf("Christopher Nolan", "Steven Spielberg", "James Cameron", "Ridley Scott"), 2),
                Question("What is the real name of Batman?", listOf("Bruce Wayne", "Clark Kent", "Tony Stark", "Peter Parker"), 0),
                Question("Which animated movie features a clownfish named Nemo?", listOf("Shark Tale", "Finding Nemo", "The Little Mermaid", "Madagascar"), 1),
                Question("What is the name of the villain in 'The Lion King'?", listOf("Mufasa", "Scar", "Zazu", "Timon"), 1)
            )
        )
    )


}