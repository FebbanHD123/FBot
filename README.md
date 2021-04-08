# Fbot
<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>FBot</summary>
  <ol>
    <li><a href="#about">About</a></li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#todo">Todo</a></li>
  </ol>
</details>



## About The Project
FBot is a Minecraft bot API to let a Minecraft client connect to servers.
See [usage](#usage) for an example


## Usage

* Add the API to your Project

* Do some magic shit ;)
  - 1: Single Bot Example (Offline/Cracked Bot):
    ```java
    public static void main(String[] args) {
          FBot bot = BotFactory.createBot("test@gamil.com", "mysafepassword");
          String host = "localhost"; //host of the server
          int port = 25565; //port of the server
          bot.connect(host, port, () -> {
              //Bot is connected
              bot.sendChatMessage("Hello im a FBot");
          });
    }
    ```
  - 2: Single Bot Example (Online Bot):
    ```java
    public static void main(String[] args) {
          FBot bot = BotFactory.createBot("example-bot");
          String host = "localhost"; //host of the server
          int port = 25565; //port of the server
          bot.connect(host, port, () -> {
              //Bot is connected
              bot.sendChatMessage("Hello im a FBot");
          });
    }
    ```
  - 3: Botter Example (Altening):
    ```java
    public static void main(String[] args) {
        String alteningApiKey = "api-key"; //the api of your altening account
        int capacity = 10; //the amount of bots that should connect
        String host = "localhost"; //host of the server
        int port = 25565; //port of the server
        FBotter botter = new TheAlteningBotter(alteningApiKey, host, port, capacity);
        botter.start();
        for(FBot bot : botter.getConnectedBots()) {
            bot.sendChatMessage("Hello im a FBot, connected from a fbotter");
        }
    }
    ```

## Todo
See the [open issues](https://github.com/FebbanHD123/FBot/issues) for a list of proposed features (and known issues).


## Contact

My Discord
 - Profile: FebanHD#5673
 - [Server](https://discord.gg/pKTfFt4fG5)
