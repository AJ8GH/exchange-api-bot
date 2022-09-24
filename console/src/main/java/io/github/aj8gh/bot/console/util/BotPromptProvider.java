package io.github.aj8gh.bot.console.util;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;

public class BotPromptProvider implements PromptProvider {

  private String prompt;

  public BotPromptProvider(String prompt) {
    this.prompt = prompt;
  }

  @Override
  public AttributedString getPrompt() {
    return new AttributedString(prompt,
        AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE));
  }

  public void setPrompt(String prompt) {
    this.prompt = prompt;
  }
}
