package Model;

/*
 *  Name of file: HelpDeskFaq.java
 *  Author:  Kushang Mistry
 *  Purpose: Lists all details about single FAQ
 *  Description: The class is responsible to fetch all details about single FAQ
 *               and store information in it.
 * */
public class HelpDeskFaq {

  private String question;

  private String answer;

  public HelpDeskFaq(String question, String answer) {
    this.question = question;
    this.answer = answer;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }
}
