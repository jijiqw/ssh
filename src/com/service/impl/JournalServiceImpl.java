package com.service.impl;

import java.util.List;

import com.bean.Article;
import com.bean.Chapter;
import com.bean.Journal;
import com.dao.JournalDAO;
import com.service.JournalService;

public class JournalServiceImpl implements JournalService {
	private JournalDAO journalDAO;
	
	public void setJournalDAO(JournalDAO journalDAO) {
		this.journalDAO = journalDAO;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List find_all() {
		return journalDAO.find_all_journal();
	}
	
	
	@Override
	public List<String> find_all_title() {
		return journalDAO.find_all_journal_title();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List find_article_of_journal(Integer journal_id) {
		return journalDAO.find_article_of_journal(journal_id);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List find_chapter_of_article(Integer article_id) {
		return journalDAO.find_chapter_of_article(article_id);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List find_paragraph_of_article(Integer chapter_id) {
		return journalDAO.find_paragraph_of_chapter(chapter_id);
	}
	
	@Override
	public Integer find_first_journal_id() {
		return journalDAO.find_first_journal_id();
	}

	@Override
	public void add_journal(String journal_title, List<String> articles_title,
			List<String> articles_outline, List<String> chapters_title) {
		Journal journal = new Journal();
		journal.setJournal_title(journal_title);
		int cur_chapter_title_index = 0;
		for (int i = 0; i < articles_title.size(); ++i) {
			Article article = new Article();
			article.setJournal(journal);
			article.setTitle(articles_title.get(i));
			article.setOutline(articles_outline.get(i));
			for (int j = 0; j < chapters_title.size() / articles_title.size(); ++j, ++cur_chapter_title_index) {
				Chapter chapter = new Chapter();
				chapter.setArticle(article);
				chapter.setTitle(chapters_title.get(cur_chapter_title_index));
				chapter.setSequence(j);
				journalDAO.add_chapter(chapter);
			}
		}
		
	}

	@Override
	public void delete_journal(Integer journal_id) {
		journalDAO.delete_journal(journal_id);
		
	}

	@Override
	public void update_article(Integer article_id, String article_title,
			String article_outline) {
		journalDAO.update_article(article_id, article_title, article_outline);
		
	}

	

	

	
	
	

}
