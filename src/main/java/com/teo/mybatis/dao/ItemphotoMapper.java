package com.teo.mybatis.dao;

import com.teo.mybatis.model.Itemphoto;
import java.util.List;

public interface ItemphotoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ITEMPHOTO
     *
     * @mbg.generated Sat Apr 03 16:24:28 EEST 2021
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ITEMPHOTO
     *
     * @mbg.generated Sat Apr 03 16:24:28 EEST 2021
     */
    int insert(Itemphoto record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ITEMPHOTO
     *
     * @mbg.generated Sat Apr 03 16:24:28 EEST 2021
     */
    Itemphoto selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ITEMPHOTO
     *
     * @mbg.generated Sat Apr 03 16:24:28 EEST 2021
     */
    List<Itemphoto> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ITEMPHOTO
     *
     * @mbg.generated Sat Apr 03 16:24:28 EEST 2021
     */
    int updateByPrimaryKey(Itemphoto record);
}