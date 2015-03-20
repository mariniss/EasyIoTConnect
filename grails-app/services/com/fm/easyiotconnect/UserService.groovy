package com.fm.easyiotconnect

import grails.transaction.Transactional

@Transactional
class UserService {


    /**
     *
     * @param sender
     * @param name
     * @param message
     * @return
     */
    boolean askQuestion(String sender, String name, String message) {
        UserQuestions question = new UserQuestions()
        question.email = sender
        question.name  = name
        question.text  = message

        question.validate()
        if(question.hasErrors()) {
            return false
        }

        question = question.save()

        return question != null
    }


    /**
     *
     * @param user
     * @return
     */
    ServiceCodes.Infos createBaseUser(String name, String country, String email, String password) {
        def newUser = new User()
        newUser.name 	 = name
        newUser.state	 = country
        newUser.email 	 = email
        newUser.password = password

        newUser.validate()
        if(newUser.hasErrors()){
            throw ServiceError.build(ServiceCodes.Errors.USER_NOT_SAVED)
        }

        User savedUser = newUser.save()
        if(savedUser == null) {
            throw ServiceError.build(ServiceCodes.Errors.USER_NOT_SAVED)
        }

        UserRole userRole = new UserRole(user : savedUser, role : Role.getBaseRole())
        if(!userRole.save()) {
            throw ServiceError.build(ServiceCodes.Errors.USER_ROLE_NOT_SAVED)
        }

        return ServiceCodes.Infos.USER_CREATED
    }

    /**
     *
     * @param userId
     * @param name
     * @param country
     * @param password
     * @return
     */
    boolean updateUser(Integer userId, String name, String country, String password) {
        boolean updated = false

        User toUpdate = User.get(userId)
        if(toUpdate != null) {
            toUpdate.name = name
            toUpdate.state = country

            if (password && password.size() > 0) {
                toUpdate.password = password

                //TODO: change also the jack passwords????!!! I don't know because we have to change the configuration
                //so I think now is not necessary but will be, and in this case I suggest a different command! or a popup
                //to inform the change
            }

            toUpdate.validate()
            if(!toUpdate.hasErrors()) {
                updated = (toUpdate.save() != null)
            }
            else {
                updated = false
            }
        }

        return updated
    }
}
