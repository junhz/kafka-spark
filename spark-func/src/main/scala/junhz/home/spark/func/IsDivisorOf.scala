package junhz.home.spark.func

object IsDivisorOf {
  
  def apply(i: Int): Int => Boolean = j => i % j == 0

}