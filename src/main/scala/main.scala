import scala.Console.println
import scala.io.{BufferedSource, Source}

object main extends App {
  val keyword : String = "documenting"
  println(keyword)

  val source: BufferedSource = Source.fromFile("word_list.txt")
  var list: List[String] = source.getLines().toList.map(_.split("\\s+").mkString("",",",""))
  var splitList : List[String] = List()
  for(line <- list) {
    val words: List[String] = line.trim.split(",").toList
    splitList = splitList ::: words
  }
  var cleanedList : List[String] = List();
  cleanedList = splitList.filterNot(findWords(keyword,splitList).toSet)
  println(cleanedList)
  source.close()

  def findWords(mainWord : String, dict : List[String]) : List[String] = {
    var notValidWords : List[String] = List()
    for (word <- dict) {
      if (!(word.length > mainWord.length)) {
        // make keyword into a set and filtering each word to see if there are letters that arent in documenting
        println("Current word: " + word + ", " + "Current keyword: " + mainWord)
        val comparedDiff = word.filterNot(mainWord.toSet)
        if (comparedDiff.isEmpty) {
          println("Anagram: " + word)
        } else {
          val tempList = List(word)
          notValidWords = notValidWords ::: tempList
          println("Not valid word due to " + comparedDiff + " not being in the keyword")
        }
      }
    }
    notValidWords
  }
}

