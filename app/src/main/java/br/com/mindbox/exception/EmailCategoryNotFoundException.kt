package br.com.mindbox.exception

import br.com.mindbox.model.email.EmailCategoryName
import br.com.mindbox.model.email.getEmailCategoryNameStrategy

class EmailCategoryNotFoundException(name: EmailCategoryName) : Exception("Email category not found: ${getEmailCategoryNameStrategy(name)}")