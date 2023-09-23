package com.examly.springapp;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringJUnit4ClassRunner.class) 
@SpringBootTest(classes = SpringappApplication.class)
@AutoConfigureMockMvc
class SpringappApplicationTests {

	@Autowired
    private MockMvc mockMvc;
	
	@Test
	public void testPostData() throws Exception {
		String st = "{\"teamId\": 1, \"teamName\": \"India\", \"sportName\": \"Throwball\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/team")
				.contentType(MediaType.APPLICATION_JSON)
				.content(st)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
	}
	
	@Test
	public void testPostPlayerData() throws Exception {
		String st = "{\"playerId\": 1, \"playerName\": \"Dhoni\", \"playerAge\": 20, \"mobileNumber\":\"1234567\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/team/1/player")
				.contentType(MediaType.APPLICATION_JSON)
				.content(st)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
	}
	

    @Test
    public void testGetTeamByID() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/team/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
    
    @Test
    public void testGetAllTeam() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/team")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testGetByPlayer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/team/1/player/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
    
    @Test
    public void testGetAllPlayer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/player")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andReturn();
    }
    

    private void checkClassExists(String className) {
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            fail("Class " + className + " does not exist.");
        }
    }

    private void checkFieldExists(String className, String fieldName) {
        try {
            Class<?> clazz = Class.forName(className);
            clazz.getDeclaredField(fieldName);
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            fail("Field " + fieldName + " in class " + className + " does not exist.");
        }
    }

	private void checkAnnotationExists(String className, String annotationName) {
		try {
			Class<?> clazz = Class.forName(className);
			ClassLoader classLoader = clazz.getClassLoader();
			Class<?> annotationClass = Class.forName(annotationName, false, classLoader);
			assertNotNull(clazz.getAnnotation((Class) annotationClass)); // Use raw type
		} catch (ClassNotFoundException | NullPointerException e) {
			fail("Class " + className + " or annotation " + annotationName + " does not exist.");
		}
	}
	

	 @Test
   public void testControllerClassExists() {
       checkClassExists("com.examly.springapp.controller.ApiController");
   }

   @Test
   public void testTeamRepoClassExists() {
       checkClassExists("com.examly.springapp.repository.TeamRepo");
   }
   
   @Test
   public void testPlayerRepoClassExists() {
       checkClassExists("com.examly.springapp.repository.PlayerRepo");
   }

   @Test
   public void testServiceClassExists() {
       checkClassExists("com.examly.springapp.service.ApiService");
   }

   @Test
   public void testTeamModelClassExists() {
       checkClassExists("com.examly.springapp.model.Team");
   }
   
   @Test
   public void testPlayerModelClassExists() {
       checkClassExists("com.examly.springapp.model.Player");
   }


   @Test
   public void testModelHasTeamNameField() {
       checkFieldExists("com.examly.springapp.model.Team", "teamName");
   }

   @Test
   public void testModelHasTeamSprotNameForField() {
       checkFieldExists("com.examly.springapp.model.Team", "sportName");
   }

   @Test
   public void testModelHasPlayerNameForField() {
       checkFieldExists("com.examly.springapp.model.Player", "playerName");
   }
   
   @Test
   public void testModelHasPlayerAgeForField() {
       checkFieldExists("com.examly.springapp.model.Player", "playerAge");
   }
   

   @Test
   public void testTeamModelHasEntityAnnotation() {
       checkAnnotationExists("com.examly.springapp.model.Team", "javax.persistence.Entity");
   }
   
   @Test
   public void testPlayerModelHasEntityAnnotation() {
       checkAnnotationExists("com.examly.springapp.model.Player", "javax.persistence.Entity");
   }

   @Test
   public void testRepoHasRepositoryAnnotation() {
       checkAnnotationExists("com.examly.springapp.repository.TeamRepo", "org.springframework.stereotype.Repository");
   }
   
   @Test
   public void testPlayerRepoHasRepositoryAnnotation() {
       checkAnnotationExists("com.examly.springapp.repository.PlayerRepo", "org.springframework.stereotype.Repository");
   }
   
   @Test
   public void testServiceHasServiceAnnotation() {
       checkAnnotationExists("com.examly.springapp.service.ApiService", "org.springframework.stereotype.Service");
   }
   
   @Test
   public void testControllerHasRestControllerAnnotation() {
       checkAnnotationExists("com.examly.springapp.controller.ApiController", "org.springframework.web.bind.annotation.RestController");
   }
   
   @Test 
   public void testControllerFolder() { 
       String directoryPath = "src/main/java/com/examly/springapp/controller"; // Replace with the path to your directory 
       File directory = new File(directoryPath); 
       assertTrue(directory.exists() && directory.isDirectory()); 
   }
   

	@Test 
   public void testModelFolder() { 
       String directoryPath = "src/main/java/com/examly/springapp/model"; // Replace with the path to your directory 
       File directory = new File(directoryPath); 
       assertTrue(directory.exists() && directory.isDirectory()); 
   }
   

	@Test 
   public void testRepositoryFolder() { 
       String directoryPath = "src/main/java/com/examly/springapp/repository"; // Replace with the path to your directory 
       File directory = new File(directoryPath); 
       assertTrue(directory.exists() && directory.isDirectory()); 
   }
   

	@Test 
   public void testServiceFolder() { 
       String directoryPath = "src/main/java/com/examly/springapp/service"; // Replace with the path to your directory 
       File directory = new File(directoryPath); 
       assertTrue(directory.exists() && directory.isDirectory()); 
   }

}
