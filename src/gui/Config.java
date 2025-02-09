package gui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles configuration of the Logo and Color Scheme of the application.
 * 
 * @author rauchas, lampedk
 * @version 1.0
 */
public class Config
{
  private String logoPath;
  private String logoWidth;
  private String logoHeight;
  private String logoX;
  private String logoY;

  private String backgroundColor;
  private String foregroundColor;
  private String buttonColor;
  private String textColor;

  private Map<String, String> settings = new HashMap<>();

  // Constructor
  public Config()
  {
    loadConfig("config.txt");
  }

  private void loadConfig(String filePath)
  {
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath)))
    {
      String line;
      while ((line = reader.readLine()) != null)
      {
        if (line.trim().isEmpty() || line.startsWith("#"))
          continue;
        String[] parts = line.split("=", 2);
        if (parts.length == 2)
        {
          settings.put(parts[0].trim(), parts[1].trim());
        }
      }
    }
    catch (IOException e)
    {
      System.err.println("Error loading config: " + e.getMessage());
    }
  }

  // Getters and setters
  public String getSetting(String key)
  {
    return settings.get(key);
  }

  public String getlogoPath()
  {
    return logoPath;
  }

  public void setLogoPath(String logoPath)
  {
    this.logoPath = logoPath;
  }

  public String getlogoWidth()
  {
    return logoWidth;
  }

  public void setLogoWidth(String logoWidth)
  {
    this.logoWidth = logoWidth;
  }

  public String getLogoHeight()
  {
    return this.logoHeight;
  }

  public void setLogoHeight(String logoHeight)
  {
    this.logoHeight = logoHeight;
  }

  public String getLogoX()
  {
    return this.logoX;
  }

  public void setLogoX(String logoX)
  {
    this.logoX = logoX;
  }

  public String getLogoY()
  {
    return this.logoY;
  }

  public void setLogoY(String logoY)
  {
    this.logoY = logoY;
  }

  public String getBackgroundColor()
  {
    return backgroundColor;
  }

  public void setBackgroundColor(String backgroundColor)
  {
    this.backgroundColor = backgroundColor;
  }

  public String getForegroundColor()
  {
    return foregroundColor;
  }

  public void setForegroundColor(String foregroundColor)
  {
    this.foregroundColor = foregroundColor;
  }

  public String getButtonColor()
  {
    return buttonColor;
  }

  public void setButtonColor(String buttonColor)
  {
    this.buttonColor = buttonColor;
  }

  public String getTextColor()
  {
    return textColor;
  }

  public void setTextColor(String textColor)
  {
    this.textColor = textColor;
  }
}
