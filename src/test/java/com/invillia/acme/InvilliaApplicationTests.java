package com.invillia.acme;

import com.google.gson.Gson;
import com.invillia.acme.controller.impl.AuthApiController;
import com.invillia.acme.model.JwtLogin;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@Slf4j
@SpringBootTest( properties = "spring.profiles.active=test", classes = InvilliaApplication.class)
@RunWith(SpringRunner.class)
public class InvilliaApplicationTests {

	protected MockMvc mvc;

	@Autowired
	AuthApiController ac;

	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.standaloneSetup(ac).build();
	}

	@Test
	public void loginInvillia() throws Exception {
		JwtLogin jwtLogin = new JwtLogin();

		jwtLogin.setPassword("r9axV;rysF,UnM-dnE5,8^G<U,;K9~$[@ndu");
		jwtLogin.setUsername("invillia");

		this.genericLogin(jwtLogin);
	}

	private void genericLogin(JwtLogin jwtLogin) throws Exception {
		Gson gson = new Gson();
		String json = gson.toJson(jwtLogin);

		MvcResult result = this.mvc.perform(post("/auth")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andReturn();

		String content = result.getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject(content);

		Assert.assertNotNull(jsonObject.getString("token"));
	}
}
