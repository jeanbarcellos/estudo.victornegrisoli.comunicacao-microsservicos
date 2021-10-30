import Sequelize from 'sequelize'

const sequelize = new Sequelize('comm-ms_auth-db', 'postgres', 'postgres', {
  host: 'localhost',
  port: 5433,
  dialect: 'postgres',
  quoteIdentifiers: false,
  define: {
    syncOnAssociation: true,
    timestamps: false,
    underscored: true,
    underscoredAll: true,
    freezeTableName: true
  }
})

sequelize
  .authenticate()
  .then(() => {
    console.info('Connection has been stablished!')
  })
  .catch(err => {
    console.error('Unable to connect to the database.')
    console.error(err.message)
  })

export default sequelize
