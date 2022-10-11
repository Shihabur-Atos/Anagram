import scala.Console.println
import scala.io.{BufferedSource, Source}

object main extends App {
  val keyword: String = "documenting"
  val source: BufferedSource = Source.fromFile("word_list.txt")
  var list: List[String] = source.getLines().toList.map(_.split("\\s+").mkString("", ",", ""))
  var splitList: List[String] = List()
  for (line <- list) {
    splitList = splitList ::: line.trim.split(",").toList
  }
  source.close()
  var cleanedList: List[String] = splitList.filterNot(findInvalidWords(keyword, splitList).toSet)
    .sortWith((s, t) => s.length < t.length) //sort in terms of size of words in ascending order
  findValidSet(keyword, cleanedList)


  def findInvalidWords(mainWord: String, dict: List[String]): List[String] = {
    var notValidWords: List[String] = List()
    for (word <- dict) {
      if (!(word.length > mainWord.length)) {
        //make keyword as a set and filter every word to have a list of words that can only be made from keyword
        val comparedDiff = word.filterNot(mainWord.toSet)
        if (comparedDiff.nonEmpty) {
          notValidWords = notValidWords ::: List(word)
        }
      }
    }
    notValidWords
  }

  def findValidSet(str: String, dict: List[String]): Unit = {
    var validSet: List[String] = List()
    var count = 0;
    var keyWord: String = str
    if (dict.size < 2) {
      System.exit(0)
    }
    else {
      for (word <- dict) {
        if (count == 2) {
          println(validSet.mkString("Pair found: " + "", ",", ""))
          findValidSet(str, dict.filterNot(validSet.toSet))
        }
        if (!(word.length > keyWord.length)) {
          // make keyword into a set and filtering each word to see if there are letters that arent in documenting
          val comparedDiff = word.filterNot(keyWord.toSet)
          if (comparedDiff.isEmpty) {
            count += 1
            validSet = validSet ::: List(word)
            keyWord = keyWord.filterNot(word.toSet)
          }
        } else {
          findValidSet(str, dict.filterNot(validSet.toSet))
        }
        if (word.equals(dict.last)) {
          findValidSet(str, dict.filterNot(validSet.toSet))
        }
      }
    }
  }
}

