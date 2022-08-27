package com.laptopapp.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.laptop.exceptions.IdNotFoundException;
import com.laptop.exceptions.LaptopNotFoundException;
import com.laptop.model.Laptop;
import com.laptopapp.util.DBQueries;

@Repository
public class LaptopRepositoryImpl implements ILaptopRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public LaptopRepositoryImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Laptop> findAll() {
		RowMapper<Laptop> mapper = new LaptopMapper();
		List<Laptop> laptops = jdbcTemplate.query(DBQueries.SELECTQUERY, mapper);
		return laptops;
	}

	@Override
	public List<Laptop> findByType(String type) throws LaptopNotFoundException {

		List<Laptop> laptops = jdbcTemplate.query(DBQueries.SELECTBYTYPE, 
				new RowMapper<Laptop>() {

			@Override
			public Laptop mapRow(ResultSet rs, int rowNum) throws SQLException {

				Laptop laptop = new Laptop();

				laptop.setName(rs.getString("name"));
				laptop.setPrice(rs.getDouble("price"));
				laptop.setType(rs.getString("type"));
				int laptopId = rs.getInt("laptop_id");
				
				return laptop;
			}

		}, type);
		return laptops;

	}

	@Override
	public List<Laptop> findByName(String name) throws LaptopNotFoundException {
		// TODO Auto-generated method stub
		List<Laptop> laptops = jdbcTemplate.query(DBQueries.SELECTBYTYPE, new RowMapper<Laptop>() {

			@Override
			public Laptop mapRow(ResultSet rs, int rowNum) throws SQLException {

				Laptop laptop = new Laptop();

				laptop.setName(rs.getString("name"));
				laptop.setPrice(rs.getDouble("price"));
				laptop.setType(rs.getString("type"));
				int LaptopId = rs.getInt("laptop_id");
			
				return laptop;
			}

		}, name);
		return laptops;
	}

	@Override
	public List<Laptop> findByPrice(double price) throws LaptopNotFoundException {
		// TODO Auto-generated method stub
		List<Laptop> laptops = jdbcTemplate.query(DBQueries.SELECTBYTYPE, new RowMapper<Laptop>() {

			@Override
			public Laptop mapRow(ResultSet rs, int rowNum) throws SQLException {

				Laptop laptop = new Laptop();
				laptop.setName(rs.getString("name"));
				laptop.setPrice(rs.getDouble("price"));
				laptop.setType(rs.getString("type"));
				int laptopId = rs.getInt("laptop_id");
				return laptop;
			}

		}, price);
		return laptops;
	}

	@Override
	public Laptop findById(int laptop_id) throws IdNotFoundException {

		return (Laptop) showAllLaptops().stream().filter(policy -> policy.getLaptopId() == laptop_id).findFirst()
				.orElseThrow(IdNotFoundException::new);
	}

	private List<Laptop> showAllLaptops() {
		return Arrays.asList(
				new Laptop("asus", "chromebook", 5, 2456.8), 
				new Laptop("dell", "macbook", 6, 2579.0),
				new Laptop("thoshiba", "netbook", 5, 26677.0), 
				new Laptop("hp", "ultrabook", 9, 2778.0));

	}

	@Override
	public void addLaptop(Laptop laptop) {
		String sql = DBQueries.INSERTQUERY;
		Object[] laptopArray = { laptop.getName(), laptop.getType(), laptop.getLaptopId(), laptop.getPrice() };

		jdbcTemplate.update(sql, laptopArray);

	}

	@Override
	public void updateLaptop(int laptop_id, double price) {
		String sql = DBQueries.UPDATEQUERY;
		jdbcTemplate.update(sql, price, laptop_id);

	}

	@Override
	public void deleteLaptop(int laptop_id) {
		String sql = DBQueries.DElETEQUERY;
		jdbcTemplate.update(sql, laptop_id);
	}

}
