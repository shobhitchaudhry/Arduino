#include <Adafruit_GFX.h>
#include <Adafruit_ST7735.h>
#include <SPI.h>

#if defined(__SAM3X8E__)
#undef __FlashStringHelper::F(string_literal)
#define F(string_literal) string_literal
#endif

#define TFT_CS  10  // Chip select line for TFT display
#define TFT_DC   8  // Data/command line for TFT
#define TFT_RST  -1  // Reset line for TFT (or connect to +5V)

Adafruit_ST7735 tft = Adafruit_ST7735(TFT_CS, TFT_DC, TFT_RST);

#define BUTTON_NONE 0
#define BUTTON_DOWN 1
#define BUTTON_RIGHT 2
#define BUTTON_SELECT 3
#define BUTTON_UP 4
#define BUTTON_LEFT 5
#define BUTTON_ONE 6


int16_t x = 7;
int16_t y = 7;
int16_t row = 1;
int16_t column = 1;
uint8_t radius = 7;
uint8_t level1radius = 7;
uint8_t level2radius = 5;
uint8_t level3radius = 5;
float score = 0;
float l1score = 0;
float l2score = 0;
float l3score = 0;
float l4score = 0;
uint16_t color = ST7735_WHITE;
boolean btnPressed = false;
boolean levelEnd;


int sensorPin = A0;    // select the input pin for the potentiometer
int ledPin = 13;      // select the pin for the LED
int sensorValue = 0;  // variable to store the value coming from the sensor
int levelNumber = 1;
char inputBuffer[10];
String playerName;
String state = "reset";

bool isNotSetup = true;

void setup() {
  Serial.begin(57600);
  tft.initR(INITR_BLACKTAB);   // initialize a ST7735S chip, black tab
}


void loop() {
  if (isNotSetup) {
    tft.fillScreen(ST7735_BLACK);
    while (true) {
      if (Serial.available() > 0) {
        state = Serial.readString();
        break;
      }
    }
    Serial.println(state);
    isNotSetup = false;
    state == "configured";
    tft.fillScreen(ST7735_BLUE);

  } else  {
    //arduinos are suggestible now
    //can be configured to do anything
    tft.fillScreen(ST7735_RED);
    while (true) {
      if (Serial.available() > 0) {
        playerName = Serial.readString();
        break;
      }
    }
    tft.fillScreen(ST7735_BLUE);

    //START GAME HERE:
    while (true) {

      radius = level1radius;
      levelEnd = false;
      tft.fillScreen(ST7735_BLACK);
      tft.setTextSize(2);
      tft.setTextColor(ST7735_RED);
      tft.setCursor(0, 10);
      tft.println("Hey ");
      tft.setCursor(0, 35);
      tft.println(playerName);
      tft.setCursor(0, 75);
      tft.print("Get Ready! ");

      //Level1 starts here
      delay(3000);
      //simple countdown:
      levelChangeAnimation(1);

      while (true) {
        uint8_t b = readButton();
        if (b == BUTTON_SELECT) {
          printonscreen();
          delay(500);
        }
        delay(100);
        drawcircles();
        if (levelEnd) {
          break;
        }
      }
      //Level2 starts here
      delay(3000);
      levelChangeAnimation(2);
      //define parameters for level(extract to function)
      x = level2radius;
      y = level2radius;
      levelNumber = 2;
      row = 1;
      column = 1;
      radius = 5;
      score = 0;
      radius = level2radius;
      levelEnd = false;
      while (true) {
        uint8_t b = readButton();
        if (b == BUTTON_SELECT) {
          printonscreen();
          delay(300);
        }
        delay(50);
        drawcircles();
        if (levelEnd) {
          break;
        }
      }

      //Level3:
      delay(3000);
      levelChangeAnimation(3);
      x = level3radius;
      y = level3radius;
      levelNumber = 3;
      row = 1;
      column = 1;
      radius = 5;
      score = 0;

      radius = level3radius;
      levelEnd = false;
      while (true) {
        uint8_t b = readButton();
        if (b == BUTTON_SELECT) {
          printonscreen();
          delay(300);
        }
        delay(25);
        drawcircles();
        if (levelEnd) {
          delay(2000);
          break;
        }
      }
      //wait for serial input to view scores:
      while (true) {
        if (Serial.available() > 0) {
          String readState = Serial.readString();
          tft.setCursor(0, 10);
          tft.fillScreen(ST7735_BLACK);
          tft.fillScreen(ST7735_BLACK);
          tft.setTextColor(ST7735_RED);
          tft.setCursor(0, 10);
          tft.print("Score:");
          tft.print(l1score + l2score + l3score);
          tft.setCursor(40, 110);
          tft.print("GG!");
          //output scores
          Serial.print("Score:l1:");
          Serial.print(l1score);
          Serial.print(";l2:");
          Serial.print(l2score);
          Serial.print(";l3:");
          Serial.print(l3score);
          Serial.println(";");

          break;
        }

      }

      //wait to start level 4
      while (true) {
        if (Serial.available() > 0) {
          String readState = Serial.readString();
          break;
        }
      }
      // Level4:
      delay(3000);
      levelChangeAnimation(4);
      x = level3radius;
      y = level3radius;
      levelNumber = 4;
      row = 1;
      column = 1;
      radius = 5;
      score = 0;
      radius = level3radius;
      levelEnd = false;
      while (true) {
        uint8_t b = readButton();
        if (b == BUTTON_SELECT) {
          printonscreenSine();
          delay(300);
        }
        delay(25);
        drawcirclesLevel4();
        if (levelEnd) {
          delay(2000);
          break;
        }
      }


      if (levelEnd) {
        tft.fillScreen(ST7735_BLACK);
        tft.setTextColor(ST7735_RED);
        tft.setCursor(0, 10);
        //score = score / radius;
        tft.print("Score:");
        tft.print(l1score + l2score + l3score + l4score);
        tft.setCursor(40, 110);
        tft.print("GG!");


        //output scores :
        while (true) {
          if (Serial.available() > 0) {
            String readState = Serial.readString();
            Serial.print("Score:l1:");
            Serial.print(l1score);
            Serial.print(";l2:");
            Serial.print(l2score);
            Serial.print(";l3:");
            Serial.print(l3score);
            Serial.print(";l4:");
            Serial.print(l4score);
            Serial.println(";");

            break;
          }
        }
        while (true) {}
      }
    }
  }
}

String readSerial() {
  while (true) {
    if (Serial.available() > 0) {
      char localBuffer[15];
      Serial.readBytes(localBuffer, 15);
      return String(localBuffer);
    }
  }
}

//to print the red circle when the button is pressed and determine the score
//we need the interrupt timing function to debounce the switch presses
void printonscreen() {
  static unsigned long last_interrupt_time = 0;
  unsigned long interrupt_time = millis();
  if (interrupt_time - last_interrupt_time > 200)
  {
    float goal = tft.height() / 2 + ((tft.height() / 2) % radius);
    float actual = column * radius * 2;
    float i = 100 - ((abs(goal - actual) / goal) * 100);
    score = score + i;
    tft.fillCircle(row * radius * 2, column * radius * 2, radius, ST7735_RED);

    row++;
    column = 1;
  }
  last_interrupt_time = interrupt_time;
}

//for level4,5
void printonscreenSine() {
  static unsigned long last_interrupt_time = 0;
  unsigned long interrupt_time = millis();
  if (interrupt_time - last_interrupt_time > 200)
  {
    float xcoord = radius / 2 + radius * row * 2;
    float goal = tft.height() / 2 + tft.height() / 8 * sin(xcoord * 6.28 / tft.width()); // y co-ords
    float actual = column * radius * 2;
    float i = 100 - ((abs(goal - actual) / goal) * 100);
    score = score + i;
    tft.fillCircle(row * radius * 2, column * radius * 2, radius, ST7735_RED);
    row++;
    column = 1;
  }
  last_interrupt_time = interrupt_time;
}


void drawcircles() {
  //overwrite old circle
  tft.drawCircle(row * radius * 2, column * radius * 2, radius, ST7735_BLACK);

  //determine if circles have gone past screen
  if (column * radius * 2 > tft.height() + radius) {
    column = 1;
    row++;
    delay(200);
  }

  if (row * radius * 2 + radius < (tft.width()) ) {
    column++;
    //falling circle:
    tft.drawCircle(row * radius * 2, column * radius * 2, radius, ST7735_WHITE);

    //guide semi circle:
    tft.drawCircle(0, tft.height() / 2 + (tft.height() / 2) % radius, radius, ST7735_WHITE);

    uint16_t x0 = 0;
    uint16_t y0 = tft.height() / 2 + ((tft.height() / 2) % radius);
    uint16_t x1 = tft.width();
    uint16_t y1 = tft.height() / 2 + ((tft.height() / 2) % radius);
    
    //line to press the joystick on:
    tft.drawLine(x0, y0, x1, y1, ST7735_WHITE);


  } else {
    tft.setTextSize(2);
    tft.setTextColor(ST7735_RED);
    tft.setCursor(0, 10);
    score = score / (row - 1);
    if (levelNumber == 1) {
      l1score = score ;
    }
    else if (levelNumber == 2) {
      l2score = score ;
    }
    else if (levelNumber == 3) {
      l3score = score ;
    }
    else if (levelNumber == 4) {
      l4score = score ;
    }

    tft.print("score:");
    tft.print(score);
    levelEnd = true;;
  }
}
//same concept as level1,2,3 but with a sine wave instead of a straight line

void drawcirclesLevel4() {
  tft.drawCircle(row * radius * 2, column * radius * 2, radius, ST7735_BLACK);
  if (column * radius * 2 > tft.height() + radius) {
    column = 1;
    row++;
    delay(200);
  }

  if (row * radius * 2 + radius < (tft.width()) ) {
    column++;
    tft.drawCircle(row * radius * 2, column * radius * 2, radius, ST7735_WHITE);
    tft.drawCircle(0, tft.height() / 2 + (tft.height() / 2) % radius, radius, ST7735_WHITE);

    uint16_t x0 = 0;
    uint16_t y0 = tft.height() / 2 + ((tft.height() / 2) % radius);
    uint16_t x1 = tft.width();
    uint16_t y1 = tft.height() / 2 + ((tft.height() / 2) % radius);

    //sine wave:
    for (int i = 1; i < tft.width(); i++)
    {
      y = tft.height() / 2 + tft.height() / 8 * sin(i * 6.28 / tft.width()); // y co-ords
      tft.drawPixel(i, y, ST7735_WHITE); // draw the wave
    }

  } else {
    tft.setTextSize(2);
    tft.setTextColor(ST7735_RED);
    tft.setCursor(0, 10);
    score = score / (row - 1);
    if (levelNumber == 1) {
      l1score = score ;
    }
    else if (levelNumber == 2) {
      l2score = score ;
    }
    else if (levelNumber == 3) {
      l3score = score ;
    }
    else if (levelNumber == 4) {
      l4score = score ;
    }
    tft.print("score:");
    tft.print(score);
    levelEnd = true;;
  }
}


void levelChangeAnimation(int8_t levelNumber) {
  tft.fillScreen(ST7735_BLACK);
  tft.setTextSize(3);
  tft.setCursor(0, 10);
  tft.print("Level ");
  tft.print(levelNumber);

  delay(1000);
  tft.fillScreen(ST7735_BLACK);
  tft.setTextColor(ST7735_BLUE);
  tft.setCursor(50, 35);

  tft.print("3");
  delay(1000);

  tft.setTextColor(ST7735_GREEN);
  tft.setCursor(50, 60);

  tft.print("2");
  delay(1000);

  tft.setTextColor(ST7735_YELLOW);
  tft.setCursor(50, 85);
  tft.print("1");
  delay(1000);

  tft.setTextColor(ST7735_RED);
  tft.setCursor(40, 110);
  tft.print("GO!");
  delay(1000);
  tft.fillScreen(ST7735_BLACK);
}


//joystick on the arduino
uint8_t readButton(void) {
  float a = analogRead(3);

  a *= 5.0;
  a /= 1024.0;

  if (a < 3.2) return BUTTON_SELECT;
  if (a < 0.2) return BUTTON_DOWN;
  if (a < 1.0) return BUTTON_RIGHT;
  if (a < 1.5) return BUTTON_SELECT;
  if (a < 2.0) return BUTTON_UP;
  if (a < 3.2) return BUTTON_LEFT;
  else return BUTTON_NONE;
}
