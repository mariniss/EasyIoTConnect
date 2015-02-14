package com.fm.easyiotconnect

/**
 * Base class to hold the user questions from landing page
 *
 * @author Fabio Marini
 */
class UserQuestions {
    String name
    String email
    String text

    static constraints = {
        name nullable: true, blank: true
    }
}
