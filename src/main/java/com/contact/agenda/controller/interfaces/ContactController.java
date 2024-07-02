package com.contact.agenda.controller.interfaces;

import com.contact.agenda.entity.Contact;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Contact Agenda", description = "Operations related to contact.")
public interface ContactController {

    @Operation(summary = "POST method to register a new contact.", description = "Contact registration")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Resource successfully registered."),
            @ApiResponse(responseCode = "400", description = "Business exception")
    })
    void saveContact(Contact contact) throws Exception;

    @Operation(summary = "GET method to retrieve a contact.", description = "Contact retrieval")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Resource successfully returned."),
            @ApiResponse(responseCode = "404", description = "Resource not found")
    })
    public Contact getContactById(@PathVariable String uuid);

    @Operation(summary = "GET method to retrieve all contacts.", description = "Contacts retrieval")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Resource successfully returned."),
            @ApiResponse(responseCode = "404", description = "Resource not found")
    })
    public List<Contact> getAllContacts();

    @Operation(summary = "DELETE method to perform deletion", description = "Contact deletion")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Resource successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Resource not found")
    })
    public void deleteContact(String uuid);
}
