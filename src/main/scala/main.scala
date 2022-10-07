import scala.Console.println
import scala.io.Source

object main extends App {
  var keyword: List[Char] = "documenting".toList

  println(keyword)

  val source = Source.fromFile("word_list.txt")
  var list: List[String] = source.getLines().toList.map(_.split("\\s+").mkString("",",",""))
  var splitList : List[String] = List()
  for(line <- list) {
    val words = line.trim.split(",").toList
    println(words + " size: " + words.size.toString)
    splitList = splitList ::: words
  }
  source.close()
  for(word <- splitList) {
    if(!(word.length > keyword.size)) {
      word
    }

  }
}

