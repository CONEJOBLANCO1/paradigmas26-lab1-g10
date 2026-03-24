import Subscription.Subscription
import Post.Post

object Main {
  def main(args: Array[String]): Unit = {
    val header = s"Reddit Post Parser\n${"=" * 40}"

    val subscriptions: List[Subscription] = FileIO.readSubscriptions("subscriptions.json")
    
    val allPosts: List[(String, String)] = subscriptions.map { subscription =>
      val (subredditName, url) = subscription
      println(s"Fetching posts from: $subredditName")
      val posts = FileIO.downloadFeed(url)
      (subredditName, posts)
    }
        
    val output = allPosts
      .map { case (url, posts) => Formatters.formatSubscription(url, posts) }
      .mkString("\n")

    println(output)
    
  }
}
