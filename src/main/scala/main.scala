import scala.:+
import scala.io.Source

object main extends App {
  var word: String = "documenting".toList.sorted.toString()
  println(word)

  val source = Source.fromFile("word_list.txt")
  var list: List[String] = List()
  for (line <- source.getLines()) {
    println(line)
    if(line.toList.sorted.toString.equals(word)) {
      println("hello")
      list = list :+ line
    }
  }
  source.close()


}