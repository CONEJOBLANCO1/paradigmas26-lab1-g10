import Subscription.Subscription

object Main {
  def main(args: Array[String]): Unit = {
    val header = s"Reddit Post Parser\n${"=" * 40}"

    val subscriptions: List[Subscription] = FileIO.readSubscriptions("subscriptions.json")
    
    val allPosts: List[Subscription] = subscriptions.map { suscription =>
      val subredditName = suscription._1
      val url = suscription._2
      println(s"Fetching posts from: $subredditName")
      val posts = FileIO.downloadFeed(url)
      (url, posts)
    }

    val output = allPosts
      .map { case (url, posts) => Formatters.formatSubscription(url, posts) }
      .mkString("\n")

    println(output)
  }
}
