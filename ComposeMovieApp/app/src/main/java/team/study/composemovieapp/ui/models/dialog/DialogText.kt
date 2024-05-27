package team.study.composemovieapp.ui.models.dialog

sealed class DialogText {
    abstract var text: String?
    abstract var id: Int?

    class Default() : DialogText() {
        override var id: Int? = null
        override var text: String? = null

        constructor(text: String) : this() {
            this.text = text
        }

        constructor(id: Int) : this() {
            this.id = id
        }
    }

    class Rating() : DialogText() {
        override var id: Int? = null
        override var text: String? = null
        var rating: Float = 0.0f

        constructor(text: String, rating: Float) : this() {
            this.text = text
            this.rating = rating
        }

        constructor(id: Int, rating: Float) : this() {
            this.id = id
            this.rating = rating
        }
    }
}