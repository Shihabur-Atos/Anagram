import scala.Console.println
import scala.io.{BufferedSource, Source}

object main extends App {

  val keyword: String = "documenting"
  val wordListFilePath: String = "word_list.txt"
  val wordsFromFile = readWordsFromFile(wordListFilePath)
  val anagramsFromFile = getAnagramsFromWordList(keyword,wordsFromFile)
  println(anagramsFromFile)
  findValidAnagramPairs(keyword,anagramsFromFile)
  def readWordsFromFile(filePath: String) : Seq[String] = {
    val filePathSource: BufferedSource = Source.fromFile(filePath)
    val getFormattedLinesFromFile: Seq[String] = filePathSource.getLines().map(_.split("\\s+").mkString("",",","")).toSeq
    getFormattedLinesFromFile.flatMap(line => line.trim.split(","))
  }

  def getAnagramsFromWordList(keyWord: String, wordsFromFile: Seq[String]): Seq[String] = {
    val word = for {
      word <- wordsFromFile
      if (word.intersect(keyWord).equals(word))
    } yield word
    word.sortWith((s,t) => s.length < t.length)
  }

  def findValidAnagramPairs(keyWord: String, anagramsFromFile: Seq[String]): Unit = {
    val filteredKeyWord: String = keyWord.filterNot(anagramsFromFile.head.toSet)
    if (!(anagramsFromFile.size < 2)) {
      for (word <- anagramsFromFile) {
        if (word.intersect(filteredKeyWord).equals(word)) {
          println("Pair found: " + anagramsFromFile.head, word)
          findValidAnagramPairs(keyWord, anagramsFromFile.filterNot(Seq(anagramsFromFile.head,word).toSet))
        }
      }
      findValidAnagramPairs(keyWord, anagramsFromFile.filterNot(Seq(anagramsFromFile.head).toSet))
    }
  }

}


