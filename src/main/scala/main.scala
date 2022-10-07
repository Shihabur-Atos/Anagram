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
  cleanedList = splitList.filterNot(findInvalidWords(keyword,splitList).toSet)
  println(cleanedList)
  source.close()


  def findInvalidWords(mainWord : String, dict : List[String]) : List[String] = {
    var notValidWords : List[String] = List()
    for (word <- dict) {
      if (!(word.length > mainWord.length)) {
        // make keyword into a set and filtering each word to see if there are letters that arent in documenting
        println("Current word: " + word + ", " + "Current keyword: " + mainWord)
        val comparedDiff = word.filterNot(mainWord.toSet)
        if (comparedDiff.nonEmpty) {
          val tempList = List(word)
          notValidWords = notValidWords ::: tempList
          println("Not valid word due to " + comparedDiff + " not being in the keyword")
        }
      }
    }
    notValidWords
  }

  def findValidWords(str : String, dict : List[String]) : Unit = {
    var validWords: List[String] = List()
    var count = 0;
    var keyWord : String = str
    for (word <- dict) {
      if(count == 2) {

      }
      if (!(word.length > keyWord.length)) {
        // make keyword into a set and filtering each word to see if there are letters that arent in documenting
        println("Current word: " + word + ", " + "Current keyword: " + keyWord)
        val comparedDiff = word.filterNot(keyWord.toSet)
        if (comparedDiff.isEmpty) {
          count += 1
          println("Anagram: " + word)
          val tempList = List(word)
          validWords = validWords ::: tempList
        }
      }
    }
  }

}

