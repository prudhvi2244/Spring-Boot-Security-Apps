package com.raj.in.model;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users_tab")
public class User {

	@Id
	@GeneratedValue
	private Integer id;
	@Column
	private String name;
	@Column
	private String username;
	@Column
	private String password;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="roles_tab",joinColumns = @JoinColumn(name="id"))
	@Column(name="role")
	private Set<String> roles;
	
}
