package br.edu.univas.lab6.smartpoll.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Question generated by hbm2java
 */
@Entity
@Table(name = "question", schema = "public")
public class Question implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private User user;
	private String title;
	private String question;
	private Date dateQuestion;
	private Date expirationDate;
	private List<Answer> answers = new ArrayList<Answer>();
	private Set<Result> results = new HashSet<Result>(0);

	public Question() {
	}

	public Question(Long id, User user, String title, String question,
			Date dateQuestion, Date expirationDate) {
		this.id = id;
		this.user = user;
		this.title = title;
		this.question = question;
		this.dateQuestion = dateQuestion;
		this.expirationDate = expirationDate;
	}

	public Question(Long id, User user, String title, String question,
			Date dateQuestion, Date expirationDate, List<Answer> answers,
			Set<Result> results) {
		this.id = id;
		this.user = user;
		this.title = title;
		this.question = question;
		this.dateQuestion = dateQuestion;
		this.expirationDate = expirationDate;
		this.answers = answers;
		this.results = results;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_gen")
	@SequenceGenerator(name = "question_gen", sequenceName = "question_id_seq", allocationSize = 1)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "title", nullable = false, length = 500)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "question", nullable = false, length = 500)
	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_question", nullable = false, length = 29)
	public Date getDateQuestion() {
		return this.dateQuestion;
	}

	public void setDateQuestion(Date dateQuestion) {
		this.dateQuestion = dateQuestion;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "expiration_date", nullable = false, length = 29)
	public Date getExpirationDate() {
		return this.expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
	public Set<Result> getResults() {
		return this.results;
	}

	public void setResults(Set<Result> results) {
		this.results = results;
	}
	
	public void addAnswer(Answer answer){
		answers.add(answer);
	}

}
